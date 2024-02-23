package com.doctor.appointment.controller;

import com.doctor.appointment.dto.appointment.*;
import com.doctor.appointment.dto.doctor.GetAllDoctors;
import com.doctor.appointment.mapper.MapperAppointment;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.service.AppointmentService;
import com.doctor.appointment.service.DoctorService;
import com.doctor.appointment.service.impl.DoctorServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor // конструктор внедряет зависимость
public class Controller {

    private DoctorService doctorService;
    private AppointmentService appointmentService;

    // ПОЛУЧИТЬ СПИСОК ВСЕХ ДОКТОРОВ
    @GetMapping("/all")
    public List<GetAllDoctors> findAll() {
        return doctorService.findAllDoctor();
    }

    // СОЗДАТЬ ПРИЁМ
    @PostMapping
    public CreateResponseAppointmentDto createAppointment(@RequestBody CreateRequestAppointmentDto createDto) {
        Appointment appointment = appointmentService.create(createDto);
        return MapperAppointment.toDto(appointment);
    }

    // ОБНОВИЬ ПРИЁМ
    @PutMapping("/{update-id}")
    public UpdateResponseAppointmentDto updateAppointment(@PathVariable ("update-id") long id,
                                                          @RequestBody UpdateRequestAppointmentDto updateDto) {
        Appointment found = appointmentService.readById(id);
        Appointment appointment = MapperAppointment.updateToEntity(updateDto, found);
        appointmentService.update(appointment);
        return MapperAppointment.updateToDto(appointment);
    }

    // ЗАПИСАТЬСЯ НА ПРИЁМ
    @PutMapping("/book/{make-id}")
    public ToMakeResponseAppointmentDto toMakeAppointment(@PathVariable ("make-id") long id,
                                                          @RequestBody ToMakeRequestAppointmentDto makeDto) {
        Appointment found = appointmentService.readById(id);
        Appointment appointment = MapperAppointment.toMakeToEntity(makeDto, found);
        appointmentService.toMake(appointment);
        return MapperAppointment.toMakeToDto(appointment);
    }

    // УДАЛИТЬ ПРИЁМ
    @DeleteMapping("/{delete-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable ("delete-id") long id) {
        appointmentService.delete(id);
    }

    // ПОЛУЧИЬ ВСЕ ПРИЁМЫ  БЕЗ ПАЦИЕНТА
    @GetMapping("/free-appointments")
    List<GetAllAppointmentByNullPatientDto> appointmentByNullPatients() {
        return appointmentService.findByPatientIsNull();
    }

    // ОТМЕНИТЬ ПРИЁМ
    @PutMapping("/cancel-appointment/{id}")
    public GetAllAppointmentByNullPatientDto cancelAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.readById(id);
        appointmentService.cancelAppointment(id);
        return new GetAllAppointmentByNullPatientDto(appointment);
    }
}
