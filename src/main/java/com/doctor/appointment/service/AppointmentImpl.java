package com.doctor.appointment.service;

import com.doctor.appointment.dto.appointment.CreateRequestAppointment;
import com.doctor.appointment.mapper.MapperAppointment;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.repository.AppointmentRepository;
import com.doctor.appointment.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentImpl implements AppointmentServes{

    private AppointmentRepository appointmentRepository;
    @Override
    public Appointment create(CreateRequestAppointment appointmentDto) {
        if(appointmentDto != null) {
            Appointment createdAppointment = MapperAppointment.toEntity(appointmentDto);
            return appointmentRepository.save(createdAppointment);
        }
        return null;
    }
}
