CREATE TABLE car_bodies(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE car_engines(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE car_transmissions(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE cars(
    id serial PRIMARY KEY,
    name varchar(255),
    body_id int REFERENCES car_bodies(id),
    engine_id int REFERENCES car_engines(id),
    transmission_id int REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies(name) VALUES ('minivan'), ('sedan'), ('hatchback'), ('jeep');
INSERT INTO car_engines(name) VALUES ('1.6l'), ('2.0l'), ('2.2l'), ('3.0l');
INSERT INTO car_transmissions(name) VALUES ('automata'), ('mechanical'), ('robot');
INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES ('BMW', null, 2, 1), ('Ford', 1, 1, 3), ('Lexus', 4, 4, 1), ('Tesla', null, 1, null),
('Lada', 2, 2, 2), ('Ferrari', 1, 1, 3), ('Shkoda', null, null, null), ('Toyota', 2, 3, null);
--1. Вывести список всех машин и все привязанные к ним детали.
SELECT cars.id, cars.name car_name, car_bodies.name body_name, car_engines.name engine_name, car_transmissions.name transmission_name
FROM cars
LEFT JOIN car_bodies ON cars.body_id = car_bodies.id
LEFT JOIN car_engines ON cars.engine_id = car_engines.id
LEFT JOIN car_transmissions ON cars.transmission_id = car_transmissions.id;
--2. Вывести кузовы, которые не используются ни в одной машине.
SELECT car_bodies.name
FROM car_bodies
LEFT JOIN cars ON car_bodies.id = cars.body_id
WHERE cars.body_id IS null;
--3. Вывести двигатели, которые не используются ни в одной машине.
SELECT car_engines.name
FROM car_engines
LEFT JOIN cars ON car_engines.id = cars.engine_id
WHERE cars.engine_id IS null;
--4. Вывести коробки передач, которые не используются ни в одной машине.
SELECT car_transmissions.name
FROM car_transmissions
LEFT JOIN cars ON car_transmissions.id = cars.transmission_id
WHERE cars.transmission_id IS null;