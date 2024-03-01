package com.doctor.appointment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "Doctors")
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

    @ManyToMany
    @JoinTable(
            name = "SpecializationsAndDoctors",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "Specialization_id")}
    )
    List<Specialization> specializations;
}
