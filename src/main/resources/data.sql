CREATE TABLE doctors
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name character varying(150) NOT NULL,
    last_name character varying(150) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO doctors (first_name, last_name)
VALUES ('John', 'Doe'),
       ('Kurt', 'Cobain');

CREATE TABLE patients
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name character varying(150) NOT NULL,
    last_name character varying(150) NOT NULL,
    mail character varying(150) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO patients (first_name, last_name, mail)
VALUES ('Pol', 'Colin', 'pol@mail.com'),
       ('Mark', 'Muller', 'mark@mail.com');

CREATE TABLE locations
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    hospital_name character varying(150) NOT NULL,
    address character varying(150) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO locations (hospital_name, address)
VALUES ('Hospital1', 'southern100'),
       ('Hospital2', 'northern 200');

CREATE TABLE appointments
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    start_date_time timestamp without time zone NOT NULL,
    end_date_time timestamp without time zone NOT NULL,
    doctor_id bigint,
    patient_id bigint,
    location_id bigint,
    PRIMARY KEY (id),
    CONSTRAINT fk_appointment_doctor_id FOREIGN KEY (doctor_id)
        REFERENCES doctors (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_appointment_patient_id FOREIGN KEY (patient_id)
        REFERENCES patients (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_appointment_location_id FOREIGN KEY (location_id)
        REFERENCES locations (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO appointments (start_date_time, end_date_time, doctor_id, patient_id, location_id)
VALUES ('2024-02-20 09:00:00', '2024-02-20 10:00:00', 1, 1, 1),
       ('2024-02-20 11:00:00', '2024-02-20 12:00:00', 1, null, 1),
       ('2024-02-20 13:00:00', '2024-02-20 14:00:00', 1, 2, 2),
       ('2024-02-20 15:00:00', '2024-02-20 16:00:00', 2, null, 2),
       ('2024-02-20 17:00:00', '2024-02-20 18:00:00', 2, null, 2);

CREATE TABLE placeOfWorkDoctors
(
    placeOfWork_id bigint NOT NULL,
    doctor_id bigint NOT NULL,
    CONSTRAINT fk_placeOfWork FOREIGN KEY (placeOfWork_id)
        REFERENCES Locations (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_doctor FOREIGN KEY (doctor_id)
        REFERENCES Doctors (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO placeOfWorkDoctors (placeOfWork_id, doctor_id)
VALUES (1,1),
       (2,2)
