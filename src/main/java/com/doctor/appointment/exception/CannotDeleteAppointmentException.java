package com.doctor.appointment.exception;

public class CannotDeleteAppointmentException extends RuntimeException{
    public CannotDeleteAppointmentException(String message) {
        super(message);
    }
}
