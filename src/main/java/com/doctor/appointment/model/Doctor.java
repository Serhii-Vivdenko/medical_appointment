package com.doctor.appointment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Entity
@Table(name = "doctors")
public
class Doctor {
    @Id
    @Generated()
    private Long id;
    private String firstName;
    private String lastName;
    private String speciality;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    List<Appointment> appointments;
}
