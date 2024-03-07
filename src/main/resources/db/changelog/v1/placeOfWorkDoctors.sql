CREATE TABLE placeOfWorkDoctors
(
    Location_id bigint NOT NULL,
    doctor_id bigint NOT NULL,
    CONSTRAINT fk_placeOfWork FOREIGN KEY (Location_id)
        REFERENCES locations (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_doctor FOREIGN KEY (doctor_id)
        REFERENCES doctors (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO placeOfWorkDoctors (Location_id, doctor_id)
VALUES (1, 1),
       (2, 2),
       (1, 3),
       (2, 3);