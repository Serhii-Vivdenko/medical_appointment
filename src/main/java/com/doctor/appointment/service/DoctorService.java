package com.doctor.appointment.service;

import com.doctor.appointment.dto.doctor.GetDoctors;
import com.doctor.appointment.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor findById(long id);
    List<GetDoctors> findAllDoctor();
    List<GetDoctors> findDoctorsBySpecializations(String specialization);
    List<GetDoctors> allDoctorsByHospital(String hospital);
}
