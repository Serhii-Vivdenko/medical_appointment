package com.doctor.appointment.service;

import com.doctor.appointment.dto.appointment.CreateRequestAppointmentDto;
import com.doctor.appointment.dto.appointment.UpdateRequestAppointmentDto;
import com.doctor.appointment.model.Appointment;

public interface AppointmentServes {
    Appointment create(CreateRequestAppointmentDto appointmentDto);
    Appointment update(Appointment appointment);
    Appointment readById(long id);

}
