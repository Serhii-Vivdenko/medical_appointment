package com.doctor.appointment.service;

import com.doctor.appointment.dto.appointment.AppointmentByNullPatientDto;
import com.doctor.appointment.dto.appointment.CreateRequestAppointmentDto;
import com.doctor.appointment.model.Appointment;

import java.util.List;

public interface AppointmentServes {
    Appointment create(CreateRequestAppointmentDto appointmentDto);
    Appointment update(Appointment appointment);
    Appointment readById(long id);
    void delete(long id);
    List<AppointmentByNullPatientDto> findByPatientIsNull();
    Appointment toMake(Appointment appointment);

}
