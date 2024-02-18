package com.doctor.appointment.mapper;

import com.doctor.appointment.dto.appointment.CreateRequestAppointment;
import com.doctor.appointment.dto.appointment.CreateResponseAppointment;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.model.Doctor;

public class MapperAppointment {
    public static Appointment toEntity(CreateRequestAppointment dto) {
        Appointment appointment = new Appointment();
        Doctor doctor = new Doctor();
        doctor.setId(dto.getDoctorId());
        appointment.setStartDateTime(dto.getStartDateTime());
        appointment.setEndDateTime(dto.getStartDateTime());
        appointment.setDoctor(doctor);
        return appointment;
    }
    public static CreateResponseAppointment toDto(Appointment appointment) {
        CreateResponseAppointment dto = new CreateResponseAppointment();
        dto.setId(appointment.getId());
        dto.setStartDateTime(appointment.getStartDateTime());
        dto.setEndDateTime(appointment.getEndDateTime());
        dto.setDoctorId(appointment.getDoctor().getId());
        return dto;
    }
}
