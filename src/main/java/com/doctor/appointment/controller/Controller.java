package com.doctor.appointment.controller;

import com.doctor.appointment.dto.appointment.*;
import com.doctor.appointment.dto.doctor.GetAllDoctors;
import com.doctor.appointment.mapper.MapperAppointment;
import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.service.AppointmentService;
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
    private AppointmentService appointmentService;

    //READ APPOINTMENT BY ID
    @GetMapping("/read/{read-id}")
    @ResponseStatus(HttpStatus.OK)
    public GetResponseReadAppointmentByIdDto readAppointmentById(@PathVariable("read-id") long id) {
        Appointment appointment = appointmentService.readById(id);
        return new GetResponseReadAppointmentByIdDto(appointment);
    }

    // ПОЛУЧИТЬ СПИСОК ВСЕХ ДОКТОРОВ
    @GetMapping("/all")
    public List<GetAllDoctors> findAll() {
        return doctorService.findAllDoctor();
    }

    // СОЗДАТЬ ПРИЁМ
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponseAppointmentDto createAppointment(@RequestBody CreateRequestAppointmentDto createDto) {
        return appointmentService.create(createDto);
    }

    // ОБНОВИЬ ПРИЁМ
    @PutMapping("/{update-id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateResponseAppointmentDto updateAppointment(@PathVariable ("update-id") long id,
                                                          @RequestBody UpdateRequestAppointmentDto updateDto) {
       return appointmentService.update(updateDto,id);
    }

    // ЗАПИСАТЬСЯ НА ПРИЁМ
    @PutMapping("/book/{make-id}")
    public ToMakeResponseAppointmentDto toMakeAppointment(@PathVariable ("make-id") long id,
                                                          @RequestBody ToMakeRequestAppointmentDto makeDto) {
        Appointment found = appointmentService.readById(id);
        Appointment appointment = MapperAppointment.toMakeToEntity(makeDto, found);
        Appointment appointmentResponse = appointmentService.toMake(appointment);
        return MapperAppointment.toMakeToDto(appointmentResponse);
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
    public CancelResponseAppointmentDto cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        Appointment appointment = appointmentService.readById(id);
        return new CancelResponseAppointmentDto(appointment);
    }
}
