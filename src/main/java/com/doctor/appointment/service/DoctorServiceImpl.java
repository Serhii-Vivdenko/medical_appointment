package com.doctor.appointment.service;

import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private DoctorRepository doctorRepository;

    @Override
    public Doctor findById(long id) {
        return doctorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Doctor not found" + id)
        );
    }

    @Override
    public List<Doctor> findAllDoctor() {
        return doctorRepository.findAll();
    }
}
