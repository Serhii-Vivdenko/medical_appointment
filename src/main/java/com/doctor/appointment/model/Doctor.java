package com.doctor.appointment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "doctors")
@ToString(exclude = {"appointments"})
public
class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String speciality;

    @JsonManagedReference
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    List<Appointment> appointments;
}
