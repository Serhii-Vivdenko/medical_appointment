package com.doctor.appointment.dto.appointment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UpdateRequestAppointmentDto {
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    Long doctorId;
    Long patientId;
    Long location;
}
