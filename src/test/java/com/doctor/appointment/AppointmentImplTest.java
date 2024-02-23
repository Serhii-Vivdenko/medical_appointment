package com.doctor.appointment;

import com.doctor.appointment.dto.appointment.CreateRequestAppointmentDto;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.repository.AppointmentRepository;
import com.doctor.appointment.service.AppointmentService;
import com.doctor.appointment.service.impl.AppointmentImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AppointmentImplTest {
    @Autowired
    private  AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void read() {
        Appointment appointment = appointmentService.readById(1);

        Assertions.assertEquals(1,appointment.getId());
    }

    @Test
    public void createAppointment() {
        CreateRequestAppointmentDto dto = new CreateRequestAppointmentDto();
        dto.setStartDateTime(LocalDateTime.parse("2024-01-20T07:30:00"));
        dto.setEndDateTime(LocalDateTime.parse("2024-01-20T08:30:00"));
        dto.setDoctorId(2L);

        Appointment created = appointmentService.create(dto);

        Assertions.assertEquals(LocalDateTime.parse("2024-01-20T07:30:00"),created.getStartDateTime());
        Assertions.assertEquals(6,created.getId());

    }
    @Test
    public void updateAppointment() {
        Appointment updated = appointmentService.readById(2);
        updated.setStartDateTime(LocalDateTime.parse("2024-01-20T11:30:00"));

        appointmentService.update(updated);
        Appointment result = appointmentService.readById(2);

        Assertions.assertEquals(LocalDateTime.parse("2024-01-20T11:30:00"), result.getStartDateTime());
    }

}

//@ExtendWith(MockitoExtension.class)
//public class AppointmentImplTest {
//    @Mock
//    private AppointmentRepository appointmentRepository;
//    @InjectMocks
//    private AppointmentImpl appointmentImpl;
//}


