-- Добавление столбца location_id
ALTER TABLE appointments
    ADD COLUMN location_id bigint;

-- Добавление внешнего ключа
ALTER TABLE appointments
    ADD CONSTRAINT fk_appointment_location_id FOREIGN KEY (location_id)
        REFERENCES locations (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

-- Вставка значений в столбец location_id
UPDATE appointments SET location_id = 1 WHERE id = 1;
UPDATE appointments SET location_id = 1 WHERE id = 2;
UPDATE appointments SET location_id = 1 WHERE id = 3;
UPDATE appointments SET location_id = 2 WHERE id = 4;
UPDATE appointments SET location_id = 2 WHERE id = 5;


