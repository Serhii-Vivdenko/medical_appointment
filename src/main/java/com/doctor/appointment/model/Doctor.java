package com.doctor.appointment.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;

//@Data
//@Entity
//@Table(name = "doctors")
public
class Doctor {
//    @Id
//    @Generated()
    private Long id;
    private String firstName;
    private String lastname;
    private String speciality;
}
