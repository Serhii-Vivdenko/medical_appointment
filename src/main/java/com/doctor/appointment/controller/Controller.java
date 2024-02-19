package com.doctor.appointment.controller;

import com.doctor.appointment.dto.appointment.*;
import com.doctor.appointment.dto.doctor.GetAllDoctors;
import com.doctor.appointment.mapper.MapperAppointment;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.service.AppointmentServes;
import com.doctor.appointment.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor // конструктор внедряет зависимость
public class Controller {

    private DoctorService doctorService;
    private AppointmentServes appointmentServes;
//    private AppointmentRepository repository;

    // получить список докторов
    @GetMapping("/all")
    public List<GetAllDoctors> findAll() {
        return doctorService.findAllDoctor();
    }

    //создать приём док
    @PostMapping
    public CreateResponseAppointmentDto createAppointment(@RequestBody CreateRequestAppointmentDto createDto) {
        Appointment appointment = appointmentServes.create(createDto);

        return MapperAppointment.toDto(appointment);
    }

    // обновить приём
    @PutMapping("/{update-id}")
    public UpdateResponseAppointmentDto updateAppointment(@PathVariable ("update-id") long id,
                                                          @RequestBody UpdateRequestAppointmentDto updateDto) {
        Appointment found = appointmentServes.readById(id);
        Appointment appointment = MapperAppointment.updateToEntity(updateDto, found);
        appointmentServes.update(appointment);
        return MapperAppointment.utoDto(appointment);
    }

    // записаться на приём


    // удалить приём
    @DeleteMapping("/{delete-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable ("delete-id") long id) {
        appointmentServes.delete(id);
    }

    // получить всех приёмы с null пациентами
    @GetMapping("/free-appointments")
    List<AppointmentByNullPatientDto> appointmentByNullPatients() {
        return appointmentServes.findByPatientIsNull();
    }


}
