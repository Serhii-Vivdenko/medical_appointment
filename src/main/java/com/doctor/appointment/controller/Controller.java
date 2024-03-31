package com.doctor.appointment.controller;

import com.doctor.appointment.dto.appointment.*;
import com.doctor.appointment.dto.doctor.GetDoctors;
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
@CrossOrigin(origins = "*")
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
    public List<GetDoctors> findAll() {
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
       return appointmentService.update(updateDto, id);
    }

    // ЗАПИСАТЬСЯ НА ПРИЁМ
    @PutMapping("/book/{make-id}")
    public ToMakeResponseAppointmentDto toMakeAppointment(@PathVariable ("make-id") long id,
                                                          @RequestBody ToMakeRequestAppointmentDto makeDto) {
        return appointmentService.toMake(makeDto, id);
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
        return appointmentService.cancelAppointment(id);
    }

    // НАЙТИ ДОКТОРОВ ПО СПЕЦИАЛЬННОСТИ
    @GetMapping("/doctor/{specialization}")
    public List<GetDoctors> findDoctorsBySpecialization(@PathVariable ("specialization") String specialization ) {
        return doctorService.findDoctorsBySpecializations(specialization);
    }

    //НАЙТИ ВСЕХ ДОКТОРОВ В БОЛЬНИЦЕ
    @GetMapping("/{hospital}")
    public List<GetDoctors> findByHospital(@PathVariable ("hospital") String hospital) {
        return doctorService.allDoctorsByHospital(hospital);
    }
}
