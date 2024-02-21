package com.doctor.appointment.service;

import com.doctor.appointment.dto.appointment.GetAllAppointmentByNullPatientDto;
import com.doctor.appointment.dto.appointment.CreateRequestAppointmentDto;
import com.doctor.appointment.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment create(CreateRequestAppointmentDto appointmentDto);
    Appointment update(Appointment appointment);
    Appointment readById(long id);
    void delete(long id);
    List<GetAllAppointmentByNullPatientDto> findByPatientIsNull();
    Appointment toMake(Appointment appointment);
    void cancelAppointment(long id);
}
