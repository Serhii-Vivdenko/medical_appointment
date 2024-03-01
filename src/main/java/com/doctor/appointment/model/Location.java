package com.doctor.appointment.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "locations")
@ToString(exclude = {"appointments"})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hospitalName;
    private String address;

    @OneToMany(mappedBy = "location")
    List<Appointment> appointments;

    @ManyToMany(mappedBy = "locations")
    List<Doctor> doctors;
}
