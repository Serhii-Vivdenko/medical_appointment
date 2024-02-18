package com.doctor.appointment.service;

import com.doctor.appointment.dto.doctor.GetAllDoctors;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<GetAllDoctors> findAllDoctor() {
        return doctorRepository.findAll().stream()
                .map(GetAllDoctors::new)
                .collect(Collectors.toList());

    }
}
