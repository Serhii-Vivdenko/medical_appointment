package com.doctor.appointment.dto.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateRequestAppointment {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime startDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime endDateTime;
    Long doctorId;




}
