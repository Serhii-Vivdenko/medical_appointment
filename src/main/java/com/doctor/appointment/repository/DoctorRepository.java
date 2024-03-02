package com.doctor.appointment.repository;

import com.doctor.appointment.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    List<Doctor> findDoctorsBySpecializations(@Param("specialization") String specialization);

    @Query(
            value = "select d.* " +
                    "from doctors d " +
                    "join PLACEOFWORKDOCTORS pl ON pl.doctor_id = d.id " +
                    "join LOCATIONS l ON l.id  =  pl.Location_id " +
                    "where l.hospital_name = :hospital",
            nativeQuery = true
    )
    List<Doctor> allDoctorsByHospital(@Param("hospital") String doctors);

}
