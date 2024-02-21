package com.doctor.appointment;

import com.doctor.appointment.repository.AppointmentRepository;
import com.doctor.appointment.service.impl.AppointmentImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppointmentImplTest {
    @Mock
    private AppointmentRepository appointmentRepository;
    @InjectMocks
    private AppointmentImpl appointmentImpl;

//    @BeforeEach
//    public void setUp() {
//        appointmentRepository = Mockito.mock(AppointmentRepository.class);
//        appointmentImpl = new AppointmentImpl(appointmentRepository);
//    }


}
