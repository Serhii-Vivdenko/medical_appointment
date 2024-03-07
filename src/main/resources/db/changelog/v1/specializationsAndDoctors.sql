CREATE TABLE specializationsAndDoctors
(
    Specialization_id bigint NOT NULL,
    doctor_id bigint NOT NULL,
    CONSTRAINT fk_specializationsAndDoctors_specialization_id FOREIGN KEY (Specialization_id)
        REFERENCES specializations (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_specializationsAndDoctors_doctor_id FOREIGN KEY (doctor_id)
        REFERENCES Doctors (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO specializationsAndDoctors (Specialization_id, doctor_id)
VALUES (1, 1),
       (2, 2),
       (3, 1),
       (1, 3);