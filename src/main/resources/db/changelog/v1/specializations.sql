CREATE TABLE specializations
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    specialization character varying(150) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO specializations (specialization)
VALUES ('surgeon'),
       ('narcologist'),
       ('therapist');
