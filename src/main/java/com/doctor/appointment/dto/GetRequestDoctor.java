package com.doctor.appointment.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetRequestDoctor {
    private String firstName;
    private String lastName;
    private String speciality;
}
