CREATE TABLE patients
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name character varying(150) NOT NULL,
    last_name character varying(150) NOT NULL,
    mail character varying(150) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.patients
    OWNER to postgres;

INSERT INTO patients (first_name, last_name, mail)
VALUES ('Pol', 'Colin', 'pol@mail.com'),
       ('Mark', 'Muller', 'mark@mail.com');