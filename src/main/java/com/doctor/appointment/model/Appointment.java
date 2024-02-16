package com.doctor.appointment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @Generated
    private Long id;
    private LocalDateTime startDataLocal;
    private LocalDateTime endDataLocal;

//    @ManyToOne
//    private
}
