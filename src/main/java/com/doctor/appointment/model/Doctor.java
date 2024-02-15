package com.doctor.appointment.model;

import lombok.Data;

@Data
public class Doctor {
    private int id;
    private String firstName;
    private String lastname;
    private String speciality;
}
