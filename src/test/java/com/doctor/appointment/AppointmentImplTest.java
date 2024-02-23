package com.doctor.appointment;

import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.repository.AppointmentRepository;
import com.doctor.appointment.service.impl.AppointmentImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
//@Transactional
public class AppointmentImplTest {
//    @Mock
    @Autowired
    private  AppointmentRepository appointmentRepository;
    private AppointmentImpl appointmentImpl;
    @BeforeEach
    void setUp() {
        appointmentImpl = new AppointmentImpl(appointmentRepository);
    }

    @Test
    public void updateAppointment() {
        Appointment updated = appointmentImpl.readById(1);
        updated.setStartDateTime(LocalDateTime.parse("2024-02-20T09:00:00"));
        appointmentImpl.update(updated);
        Appointment result = appointmentImpl.readById(1);
        Assertions.assertEquals(LocalDateTime.parse("2024-02-20T09:00:00"), result.getStartDateTime());

    }
}
