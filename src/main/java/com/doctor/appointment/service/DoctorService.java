package com.doctor.appointment.service;

import com.doctor.appointment.model.Doctor;

import java.util.List;
import java.util.Locale;

public interface DoctorService {
    Doctor findById(long id);
    List<Doctor> findAllDoctor();
}
