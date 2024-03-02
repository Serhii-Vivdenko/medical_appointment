package com.doctor.appointment.repository;

import com.doctor.appointment.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DoctorRepository extends JpaRepository<Doctor,Long> {


    @Query(
            value = "SELECT d.* " +
                    "FROM doctors d " +
                    "JOIN SPECIALIZATIONSANDDOCTORS sd ON d.id = sd.doctor_id " +
                    "JOIN SPECIALIZATIONS s ON sd.specialization_id = s.id " +
                    "WHERE s.specialization = :specialization",
            nativeQuery = true
    )
    List<Doctor> findDoctorsBySpecializations(String specialization);

}
