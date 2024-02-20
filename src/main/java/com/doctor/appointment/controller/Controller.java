package com.doctor.appointment.controller;

import com.doctor.appointment.dto.appointment.*;
import com.doctor.appointment.dto.doctor.GetAllDoctors;
import com.doctor.appointment.mapper.MapperAppointment;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.repository.AppointmentRepository;
import com.doctor.appointment.service.AppointmentServes;
import com.doctor.appointment.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor // конструктор внедряет зависимость
public class Controller {

    private DoctorService doctorService;
    private AppointmentServes appointmentServes;

    // ПОЛУЧИТЬ СПИСОК ВСЕХ ДОКТОРОВ
    @GetMapping("/all")
    public List<GetAllDoctors> findAll() {
        return doctorService.findAllDoctor();
    }

    // СОЗДАТЬ ПРИЁМ
    @PostMapping
    public CreateResponseAppointmentDto createAppointment(@RequestBody CreateRequestAppointmentDto createDto) {
        Appointment appointment = appointmentServes.create(createDto);

        return MapperAppointment.toDto(appointment);
    }

    // ОБНОВИЬ ПРИЁМ
    @PutMapping("/{update-id}")
    public UpdateResponseAppointmentDto updateAppointment(@PathVariable ("update-id") long id,
                                                          @RequestBody UpdateRequestAppointmentDto updateDto) {
        Appointment found = appointmentServes.readById(id);
        Appointment appointment = MapperAppointment.updateToEntity(updateDto, found);
        appointmentServes.update(appointment);
        return MapperAppointment.updateToDto(appointment);
    }

    // ЗАПИСАТЬСЯ НА ПРИЁМ
    @PutMapping("/to-make/{make-id}")
    public ToMakeResponseAppointmentDto toMakeAppointment(@PathVariable ("make-id") long id,
                                                          @RequestBody ToMakeRequestAppointmentDto makeDto) {
        Appointment found = appointmentServes.readById(id);
        Appointment appointment = MapperAppointment.toMakeToEntity(makeDto, found);
        appointmentServes.toMake(appointment);
        return MapperAppointment.toMakeToDto(appointment);
    }

    // УДАЛИТЬ ПРИЁМ
    @DeleteMapping("/{delete-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable ("delete-id") long id) {
        appointmentServes.delete(id);
    }

    // ПОЛУЧИЬ ВСЕ ПРИЁМЫ С NULL
    @GetMapping("/free-appointments")
    List<GetAllAppointmentByNullPatientDto> appointmentByNullPatients() {
        return appointmentServes.findByPatientIsNull();
    }

    // ОТМЕНИТЬ ПРИЁМ
    @PutMapping("/cancel-appointment/{id}")
    public GetAllAppointmentByNullPatientDto cancelAppointment(@PathVariable Long id) {
        appointmentServes.cancelAppointment(id);
        Appointment appointment = appointmentServes.readById(id);
        return new GetAllAppointmentByNullPatientDto(appointment);
    }
}
