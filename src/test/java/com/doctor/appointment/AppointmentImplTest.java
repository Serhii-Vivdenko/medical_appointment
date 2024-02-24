package com.doctor.appointment;

import com.doctor.appointment.dto.appointment.CreateRequestAppointmentDto;
import com.doctor.appointment.dto.appointment.GetAllAppointmentByNullPatientDto;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.repository.AppointmentRepository;
import com.doctor.appointment.service.AppointmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
public class AppointmentImplTest {
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
    @Test
    public void findByPatientIsNull() {
        List<GetAllAppointmentByNullPatientDto> appointmentListNull = appointmentService.findByPatientIsNull();

        Assertions.assertEquals(3,appointmentListNull.size());
    }
    @Test
    public void deleteAppointment() {
        appointmentService.delete(5);
    }

}


