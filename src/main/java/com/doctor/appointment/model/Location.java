package com.doctor.appointment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hospitalName;
    private String address;

    @ManyToMany(mappedBy = "locations")
    List<Doctor> doctors;
}
