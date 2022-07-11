CREATE TABLE devices(
    id serial PRIMARY KEY,
    name varchar(255),
    price float
);

CREATE TABLE people(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE devices_people(
    id serial PRIMARY KEY,
    device_id int REFERENCES devices(id),
    people_id int REFERENCES people(id)
);

INSERT INTO devices(name, price)
VALUES
('Apple', 59999.99),
('Samsung', 37999.99),
('Nokia', 2999.99),
('Xiaomi', 10000.99);

INSERT INTO people(name)
VALUES
('Artem'),
('Evgeniy'),
('Alexandr'),
('Maxim'),
('Katya');

INSERT INTO devices_people(device_id, people_id)
VALUES
(1, 1),
(3, 1),
(1, 5),
(4, 5),
(2, 2),
(3, 3),
(4, 4);

SELECT AVG(price)
FROM devices;

SELECT people.name, AVG (devices.price) AS average_price
FROM devices_people AS dp
JOIN devices ON dp.device_id = devices.id
JOIN people ON dp.people_id = people.id
GROUP BY people.name;

SELECT people.name, AVG (devices.price) AS average_price
FROM devices_people AS dp
JOIN devices ON dp.device_id = devices.id
JOIN people ON dp.people_id = people.id
GROUP BY people.name
HAVING AVG (devices.price) > 5000;