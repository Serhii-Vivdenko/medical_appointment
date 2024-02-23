package com.doctor.appointment;

import com.doctor.appointment.controller.Controller;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.model.Patient;
import com.doctor.appointment.repository.AppointmentRepository;
import com.doctor.appointment.service.AppointmentService;
import com.doctor.appointment.service.impl.AppointmentImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ControllerTest {
    @Mock
    private AppointmentService appointmentService;
    @InjectMocks
    private Controller controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
//    @Test
//    public void getAllDoctors() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/all"));
//    }

    @Test
    void test() {
        Assertions.assertTrue(true);
    }
}
