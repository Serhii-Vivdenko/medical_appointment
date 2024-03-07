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