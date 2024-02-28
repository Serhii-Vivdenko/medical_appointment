package com.doctor.appointment.service.impl;

import com.doctor.appointment.dto.appointment.*;
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
    public CreateResponseAppointmentDto create(CreateRequestAppointmentDto appointmentDto) {
        Appointment createdAppointment = MapperAppointment.toEntity(appointmentDto);
        Appointment appointmentResponse = appointmentRepository.save(createdAppointment);
        return MapperAppointment.toDto(appointmentResponse);
    }

    @Override
    public Appointment readById(long id) {
        return appointmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Appointment " + id + " not found!!!")
        );
    }

   @Override
    public UpdateResponseAppointmentDto update(UpdateRequestAppointmentDto dto, long id) {
        Appointment found = readById(id);
        Appointment appointment = MapperAppointment.updateToEntity(dto, found);
        Appointment responseAppointment = appointmentRepository.save(appointment);
        return MapperAppointment.updateToDto(responseAppointment);
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
    public ToMakeResponseAppointmentDto toMake(ToMakeRequestAppointmentDto dto, long id) {
        Appointment found = readById(id);
        Appointment appointment = MapperAppointment.toMakeToEntity(dto, found);
        Appointment appointmentResponse = appointmentRepository.save(appointment);
        return MapperAppointment.toMakeToDto(appointmentResponse);
    }

    @Override
    public void cancelAppointment(long id) {
        appointmentRepository.cancelAppointmentPatient(id);
    }
}
