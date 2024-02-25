package com.doctor.appointment.dto.appointment;

import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.model.Patient;
import io.micrometer.common.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class GetResponseReadAppointmentByIdDto {
    Long id;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    Long doctorId;
    Long patientId;

    public GetResponseReadAppointmentByIdDto(Appointment appointment) {
        id = appointment.getId();
        startDateTime = appointment.getStartDateTime();
        endDateTime = appointment.getEndDateTime();
        doctorId = appointment.getDoctor().getId();
        if (appointment.getPatient() == null){
            patientId = null;
        } else {
            patientId = appointment.getPatient().getId();
        }

    }

}
