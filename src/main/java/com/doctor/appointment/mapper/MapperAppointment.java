package com.doctor.appointment.mapper;

import com.doctor.appointment.dto.appointment.*;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.model.Patient;

public class MapperAppointment {
    public static Appointment toEntity(CreateRequestAppointmentDto dto) {
        Appointment appointment = new Appointment();
        Doctor doctor = new Doctor();
        doctor.setId(dto.getDoctorId());
        appointment.setStartDateTime(dto.getStartDateTime());
        appointment.setEndDateTime(dto.getEndDateTime());
        appointment.setDoctor(doctor);
        return appointment;
    }
    public static CreateResponseAppointmentDto toDto(Appointment appointment) {
        CreateResponseAppointmentDto dto = new CreateResponseAppointmentDto();
        dto.setId(appointment.getId());
        dto.setStartDateTime(appointment.getStartDateTime());
        dto.setEndDateTime(appointment.getEndDateTime());
        dto.setDoctorId(appointment.getDoctor().getId());
        return dto;
    }

    public static Appointment updateToEntity(UpdateRequestAppointmentDto dto, Appointment appointment) {

        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setId(appointment.getId());

        Patient patient = new Patient();
        patient.setId(dto.getPatientId());

        Doctor doctor = new Doctor();
        doctor.setId(dto.getDoctorId());

        if (dto.getStartDateTime() != null) {
            updatedAppointment.setStartDateTime(dto.getStartDateTime());
        } else {
            updatedAppointment.setStartDateTime(appointment.getStartDateTime());
        }

        if(dto.getEndDateTime() != null) {
            updatedAppointment.setEndDateTime(dto.getEndDateTime());
        } else {
            updatedAppointment.setEndDateTime(appointment.getEndDateTime());
        }

        updatedAppointment.setPatient(patient);
        updatedAppointment.setDoctor(doctor);
        return updatedAppointment;
    }
    public static UpdateResponseAppointmentDto updateToDto(Appointment appointment) {
        UpdateResponseAppointmentDto dto = new UpdateResponseAppointmentDto();
        dto.setId(appointment.getId());
        dto.setStartDateTime(appointment.getStartDateTime());
        dto.setEndDateTime(appointment.getEndDateTime());
        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setPatientId(appointment.getPatient().getId());
        return dto;
    }

    public static Appointment toMakeToEntity(ToMakeRequestAppointmentDto dto, Appointment appointment) {
        Appointment toMakeAppointment = new Appointment();
        Patient patient = new Patient();
        patient.setId(dto.getPatientId());

        toMakeAppointment.setId(appointment.getId());
        toMakeAppointment.setDoctor(appointment.getDoctor());
        toMakeAppointment.setStartDateTime(appointment.getStartDateTime());
        toMakeAppointment.setEndDateTime(appointment.getEndDateTime());

        toMakeAppointment.setPatient(patient);

        return toMakeAppointment;
    }
    public static ToMakeResponseAppointmentDto toMakeToDto(Appointment appointment) {
        ToMakeResponseAppointmentDto dto = new ToMakeResponseAppointmentDto();
        dto.setId(appointment.getId());
        dto.setStartDateTime(appointment.getStartDateTime());
        dto.setEndDateTime(appointment.getEndDateTime());
        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setPatientId(appointment.getPatient().getId());
        return dto;
    }


}
