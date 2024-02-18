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
@Table(name = "patients")
@ToString(exclude = {"appointments"})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String mail;

    @JsonManagedReference
    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    List<Appointment> appointments;

}
