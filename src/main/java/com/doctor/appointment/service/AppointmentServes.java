package com.doctor.appointment.service;

import com.doctor.appointment.dto.appointment.CreateRequestAppointment;
import com.doctor.appointment.model.Appointment;

public interface AppointmentServes {
    Appointment create(CreateRequestAppointment appointmentDto);

}
