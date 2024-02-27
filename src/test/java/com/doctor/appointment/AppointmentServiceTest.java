package com.doctor.appointment;

import com.doctor.appointment.controller.Controller;
import com.doctor.appointment.dto.appointment.CreateRequestAppointmentDto;
import com.doctor.appointment.dto.appointment.GetAllAppointmentByNullPatientDto;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.model.Patient;
import com.doctor.appointment.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Rollback
@SpringBootTest
public class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private Controller controller;
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

//        ResponseEntity<CreateResponseAppointmentDto> responseEntity = controller.createAppointment(dto);
        Appointment created = appointmentService.readById(6);

//        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(LocalDateTime.parse("2024-01-20T07:30:00"),created.getStartDateTime());
        Assertions.assertEquals(6,created.getId());
    }
    @Test
    public void updateAppointment() {
        Appointment appointment = appointmentService.readById(2);
        appointment.setStartDateTime(LocalDateTime.parse("2024-01-20T11:30:00"));

//        appointmentService.update(appointment);
        Appointment updated = appointmentService.readById(2);

//        Assertions.assertEquals(LocalDateTime.parse("2024-01-20T11:30:00"), updated.getStartDateTime());
    }
    @Test
    public void findByPatientIsNull() {
        List<GetAllAppointmentByNullPatientDto> appointmentListNull = appointmentService.findByPatientIsNull();

        Assertions.assertEquals(3,appointmentListNull.size());
    }

    @Test
    public void toMake() {
        Patient patient = new Patient();
        patient.setId(2L);
        Appointment appointment = appointmentService.readById(2);
        appointment.setPatient(patient);

        appointmentService.toMake(appointment);

        Assertions.assertEquals(2,appointment.getPatient().getId());
    }

    @Test
    public void deleteAppointment() {
        appointmentService.delete(5);
    }

    @Test
    public void cancel() {
        appointmentService.cancelAppointment(5);
        Appointment appointment = appointmentService.readById(5);

        Assertions.assertNull(appointment.getPatient());
    }



}


