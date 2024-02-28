package com.doctor.appointment.service;

import com.doctor.appointment.dto.appointment.*;
import com.doctor.appointment.model.Appointment;

import java.util.List;

public interface AppointmentService {
    CreateResponseAppointmentDto create(CreateRequestAppointmentDto appointmentDto);
    UpdateResponseAppointmentDto update(UpdateRequestAppointmentDto dto, long id);
    Appointment readById(long id);
    void delete(long id);
    List<GetAllAppointmentByNullPatientDto> findByPatientIsNull();
    ToMakeResponseAppointmentDto toMake(ToMakeRequestAppointmentDto appointment, long id);
    CancelResponseAppointmentDto cancelAppointment(long id);
}
