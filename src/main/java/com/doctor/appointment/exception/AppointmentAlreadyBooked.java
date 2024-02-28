package com.doctor.appointment.exception;

public class AppointmentAlreadyBooked extends RuntimeException{
    public AppointmentAlreadyBooked(String message) {
        super(message);
    }
}
