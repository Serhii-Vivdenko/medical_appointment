package com.doctor.appointment.dto.appointment;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
//@Builder
public class CreateRequestAppointmentDto {
    @NonNull
    LocalDateTime startDateTime;
    @NonNull
    LocalDateTime endDateTime;
    Long doctorId;
}
