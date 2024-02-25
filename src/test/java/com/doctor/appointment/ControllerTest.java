package com.doctor.appointment;

import com.doctor.appointment.controller.Controller;
import com.doctor.appointment.dto.appointment.CreateRequestAppointmentDto;
import com.doctor.appointment.dto.appointment.UpdateRequestAppointmentDto;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        mockMvc.perform(get("/api/read/{read-id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.startDateTime").value("2024-02-20T09:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("2024-02-20T10:00:00"))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.patientId").value(1));

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
}
