CREATE TABLE doctors
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name character varying(150) NOT NULL,
    last_name character varying(150) NOT NULL,
    speciality character varying(150) NOT NULL,
    PRIMARY KEY (id)
);
