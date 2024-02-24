package com.doctor.appointment;

import com.doctor.appointment.controller.Controller;
import com.doctor.appointment.dto.appointment.CreateRequestAppointmentDto;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.model.Patient;
import com.doctor.appointment.repository.AppointmentRepository;
import com.doctor.appointment.service.AppointmentService;
import com.doctor.appointment.service.DoctorService;
import com.doctor.appointment.service.impl.AppointmentImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = Controller.class)
@ExtendWith(MockitoExtension.class)
class ControllerTest {
    @Mock
    private AppointmentService appointmentService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private Controller controller;

    private CreateRequestAppointmentDto createDto;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
//        createDto = CreateRequestAppointmentDto
//                .builder()
//                .startDateTime(LocalDateTime.parse("2024-02-20T09:00:00"))
//                .endDateTime(LocalDateTime.parse("2024-02-20T10:00:00"))
//                .doctorId(2L)
//                .build();
    }

    @Test
    void createAppointment() throws Exception {
        CreateRequestAppointmentDto dto = new CreateRequestAppointmentDto();
        dto.setId(6L);
        dto.setStartDateTime(LocalDateTime.parse("2024-02-20T09:00:00"));
        dto.setEndDateTime(LocalDateTime.parse("2024-02-20T10:00:00"));


        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }


}
