package com.doctor.appointment.dto.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateRequestAppointmentDto {
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    Long doctorId;
}
