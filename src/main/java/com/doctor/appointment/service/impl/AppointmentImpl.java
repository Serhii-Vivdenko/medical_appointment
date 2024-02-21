package com.doctor.appointment.service.impl;

import com.doctor.appointment.dto.appointment.GetAllAppointmentByNullPatientDto;
import com.doctor.appointment.dto.appointment.CreateRequestAppointmentDto;
import com.doctor.appointment.mapper.MapperAppointment;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.repository.AppointmentRepository;
import com.doctor.appointment.service.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    @Override
    public Appointment create(CreateRequestAppointmentDto appointmentDto) {
        if(appointmentDto != null) {
            Appointment createdAppointment = MapperAppointment.toEntity(appointmentDto);
            return appointmentRepository.save(createdAppointment);
        }
        return null;
    }

    @Override
    public Appointment readById(long id) {
        return appointmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Appointment " + id + " not found!!!")
        );
    }

    @Override
    public Appointment update(Appointment appointment) {
        if (appointment != null) {
            return appointmentRepository.save(appointment);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        Appointment appointment = readById(id);
        appointmentRepository.delete(appointment);
    }

    @Override
    public List<GetAllAppointmentByNullPatientDto> findByPatientIsNull() {
        return appointmentRepository.findByPatientIsNull().stream()
                .map(GetAllAppointmentByNullPatientDto:: new)
                .collect(Collectors.toList());
    }

    @Override
    public Appointment toMake(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void cancelAppointment(long id) {
        appointmentRepository.cancelAppointmentPatient(id);
    }
}
