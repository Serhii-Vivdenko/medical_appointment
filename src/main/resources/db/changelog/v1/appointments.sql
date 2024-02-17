CREATE TABLE public.appointments
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    start_date_time timestamp without time zone NOT NULL,
    end_date_time timestamp without time zone NOT NULL,
    doctor_id bigint,
    patient_id bigint,
    PRIMARY KEY (id),
    CONSTRAINT fk_appointment_doctor_id FOREIGN KEY (doctor_id)
        REFERENCES public.doctors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_appointment_patient_id FOREIGN KEY (patient_id)
        REFERENCES public.patients (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.appointments
    OWNER to postgres;