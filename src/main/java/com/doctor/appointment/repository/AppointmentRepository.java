package com.doctor.appointment.repository;

import com.doctor.appointment.model.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientIsNull();
    @Transactional
    @Modifying
    @Query("UPDATE Appointment a SET a.patient = null WHERE a.id = :appointmentId")
    void cancelAppointmentPatient(@Param("appointmentId") Long appointmentId);
//    @Query(value = "UPDATE appointments SET patient_id = null WHERE id = ?1", nativeQuery = true)
//    void cancelAppointmentPatient(long appointmentId);

}
