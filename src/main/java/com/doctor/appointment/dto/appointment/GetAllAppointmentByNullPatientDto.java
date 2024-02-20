package com.doctor.appointment.dto.appointment;

import com.doctor.appointment.model.Appointment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetAllAppointmentByNullPatientDto {
    Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime startDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime endDateTime;
    Long doctorId;
    Long patientId;

    public GetAllAppointmentByNullPatientDto(Appointment appointment) {
        id = appointment.getId();
        startDateTime = appointment.getStartDateTime();
        endDateTime = appointment.getEndDateTime();
        doctorId = appointment.getDoctor().getId();
        if (appointment.getPatient() != null) {
            patientId = appointment.getPatient().getId();
        } else {
            patientId = null;
        }
    }
}
