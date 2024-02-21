package com.doctor.appointment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
//@ToString(exclude = {"doctor", "patient"})
@Entity
@Table(name = "appointments")
public final class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = true)
    private Patient patient;

}
