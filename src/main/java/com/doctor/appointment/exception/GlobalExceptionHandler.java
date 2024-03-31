package com.doctor.appointment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AppointmentAlreadyBooked.class)
    public ResponseEntity<String> handleBadRequestException(AppointmentAlreadyBooked e) {
        log.warn("Appointment already booked");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CannotDeleteAppointmentException.class)
    public ResponseEntity<String> handleCannotDeleteAppointmentException(CannotDeleteAppointmentException e) {
        log.warn("Appointment with existing patient cannot be deleted");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
