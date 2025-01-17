package com.doctor.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
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

    @JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    List<Appointment> appointments;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "SpecializationsAndDoctors",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "Specialization_id")}
    )
    List<Specialization> specializations;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "PlaceOfWorkDoctor",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "placeOfWork_id")}
    )
    List<Location> locations;

}
