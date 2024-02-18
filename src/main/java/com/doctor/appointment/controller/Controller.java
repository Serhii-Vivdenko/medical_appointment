package com.doctor.appointment.controller;

import com.doctor.appointment.dto.appointment.CreateRequestAppointment;
import com.doctor.appointment.dto.appointment.CreateResponseAppointment;
import com.doctor.appointment.dto.doctor.GetAllDoctors;
import com.doctor.appointment.mapper.MapperAppointment;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.service.AppointmentServes;
import com.doctor.appointment.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@AllArgsConstructor // конструктор внедряет зависимость
public class Controller {

    private DoctorService doctorService;
    private AppointmentServes appointmentServes;

    // получить список докторов
    @GetMapping("/all")
    public List<GetAllDoctors> findAll() {
        return doctorService.findAllDoctor();
    }
    //создать приём докторов

    @PutMapping
    public CreateResponseAppointment createAppointment(@RequestBody CreateRequestAppointment createAppointment) {
        Appointment appointment = appointmentServes.create(createAppointment);
        return MapperAppointment.toDto(appointment);

    }


}
