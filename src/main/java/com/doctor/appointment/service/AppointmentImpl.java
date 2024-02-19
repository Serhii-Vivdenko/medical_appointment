package com.doctor.appointment.service;

import com.doctor.appointment.dto.appointment.AppointmentByNullPatient;
import com.doctor.appointment.dto.appointment.CreateRequestAppointmentDto;
import com.doctor.appointment.dto.appointment.UpdateRequestAppointmentDto;
import com.doctor.appointment.mapper.MapperAppointment;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentImpl implements AppointmentServes{

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
                () -> new EntityNotFoundException("Appointment " + id + "not found")
        );
    }

    @Override
    public Appointment update(Appointment appointment) {
        if (appointment != null) {
//            Appointment found = readById(appointment.getId());
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
    public List<AppointmentByNullPatient> findByPatientIsNull() {
        return appointmentRepository.findByPatientIsNull().stream()
                .map(AppointmentByNullPatient :: new)
                .collect(Collectors.toList());
    }
}
