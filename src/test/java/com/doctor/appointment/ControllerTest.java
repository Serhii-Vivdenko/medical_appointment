package com.doctor.appointment;

import com.doctor.appointment.controller.Controller;
import com.doctor.appointment.dto.appointment.CreateRequestAppointmentDto;
import com.doctor.appointment.dto.appointment.GetAllAppointmentByNullPatientDto;
import com.doctor.appointment.dto.appointment.ToMakeRequestAppointmentDto;
import com.doctor.appointment.dto.appointment.UpdateRequestAppointmentDto;
import com.doctor.appointment.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@Transactional
@Rollback
@AutoConfigureMockMvc
class ControllerTest {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private Controller controller;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void read() throws Exception {
        mockMvc.perform(get("/api/read/{read-id}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.startDateTime").value("2024-02-20T11:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("2024-02-20T12:00:00"))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.patientId", nullValue()));

    }

    @Test
    public void createAppointment() throws Exception {
        CreateRequestAppointmentDto dto = new CreateRequestAppointmentDto();
        dto.setStartDateTime(LocalDateTime.parse("2024-02-20T09:00:00"));
        dto.setEndDateTime(LocalDateTime.parse("2024-02-20T10:00:00"));
        dto.setDoctorId(2L);

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.startDateTime").value("2024-02-20T09:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("2024-02-20T10:00:00"))
                .andExpect(jsonPath("$.doctorId").value(2));
    }

    @Test
    public void updateAppointment() throws Exception {
        UpdateRequestAppointmentDto dto = new UpdateRequestAppointmentDto();
        dto.setStartDateTime(LocalDateTime.parse("2024-02-20T09:00:00"));
        dto.setDoctorId(1L);
        dto.setPatientId(1L);

        mockMvc.perform(put("/api/{update-id}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDateTime").value("2024-02-20T09:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("2024-02-20T12:00:00"))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.patientId").value(1));

    }
    @Test
    public void updateAppointmentPatientNull() throws Exception {
        UpdateRequestAppointmentDto dto = new UpdateRequestAppointmentDto();
        dto.setStartDateTime(LocalDateTime.parse("2024-02-20T09:00:00"));
        dto.setDoctorId(1L);
        dto.setPatientId(null);

        mockMvc.perform(put("/api/{update-id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId", nullValue()));

    }


    @Test
    public void findByPatientIsNull() throws Exception {
        List<GetAllAppointmentByNullPatientDto> appointmentListNull = appointmentService.findByPatientIsNull();

        mockMvc.perform(get("/api//free-appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appointmentListNull)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(appointmentListNull.size())));// ожидаемый размер этого корневого элемента должен быть равен размеру списка appointmentListNull.
    }

    @Test
    public void deleteAppointment() throws Exception {
        mockMvc.perform(delete("/api/{delete-id}", 2))
                .andExpect(status().isNoContent());
    }

    @Test
    public void toMake() throws Exception {
        ToMakeRequestAppointmentDto dto = new ToMakeRequestAppointmentDto();
        dto.setPatientId(2L);

//        Appointment appointment = appointmentService.readById(1);

        mockMvc.perform(put("/api/book/{make-id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId").value(2));

//        Assertions.assertEquals(2, appointment.getPatient().getId());
    }

    @Test
    public void canselAppointment() throws Exception {
//        Appointment appointment = appointmentService.readById(3);
        mockMvc.perform(put("/api/cancel-appointment/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId", nullValue()));
    }


}
