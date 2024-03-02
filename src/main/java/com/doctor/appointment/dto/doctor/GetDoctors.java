package com.doctor.appointment.dto.doctor;

import com.doctor.appointment.model.Doctor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetDoctors {
    private Long id;
    private String firstName;
    private String lastName;

    public GetDoctors(Doctor doctor) {
        id = doctor.getId();
        firstName = doctor.getFirstName();
        lastName = doctor.getLastName();
    }
}
