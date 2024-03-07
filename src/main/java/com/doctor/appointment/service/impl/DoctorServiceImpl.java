package com.doctor.appointment.service.impl;

import com.doctor.appointment.dto.doctor.GetDoctors;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.repository.DoctorRepository;
import com.doctor.appointment.service.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    @Override
    public Doctor findById(long id) {
        return doctorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Doctor " + id + "not found")
        );
    }

    @Override
    public List<GetDoctors> findAllDoctor() {
        return doctorRepository.findAll().stream()
                .map(GetDoctors::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<GetDoctors> findDoctorsBySpecializations(String specialization) {
        List<Doctor> doctors = doctorRepository.findDoctorsBySpecializations(specialization);

        if (doctors.isEmpty()) {
            throw new EntityNotFoundException("No doctors " + specialization);
        }

        return doctors.stream().map(GetDoctors::new).collect(Collectors.toList());
    }

    @Override
    public List<GetDoctors> allDoctorsByHospital(String hospital) {
        return doctorRepository.allDoctorsByHospital(hospital)
                .stream().map(GetDoctors::new)
                .collect(Collectors.toList());
    }
}
