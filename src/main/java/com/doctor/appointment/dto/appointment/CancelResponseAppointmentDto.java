package com.doctor.appointment.dto.appointment;

import com.doctor.appointment.model.Appointment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CancelResponseAppointmentDto {
    Long id;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    Long doctorId;
    Long patientId;

    public CancelResponseAppointmentDto(Appointment appointment) {
        id = appointment.getId();
        startDateTime = appointment.getStartDateTime();
        endDateTime = appointment.getEndDateTime();
        doctorId = appointment.getDoctor().getId();
        patientId = null;
    }
}