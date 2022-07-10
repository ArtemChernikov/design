CREATE TABLE fauna (
    id serial PRIMARY KEY,
    name text,
    avg_age int,
    discovery_date date
);

INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('bird', 10, '1998-05-25'),
('tiger', 25, '1970-06-11'),
('elephant', 50, '1955-09-14'),
('mouse', 2, '1900-03-01'),
('horse', 25, '1500-01-01'),
('dinohorse', 16000, null),
('fish', 1, '1455-02-03');

SELECT *
FROM fauna
WHERE name LIKE '%fish%';

SELECT *
FROM fauna
WHERE avg_age BETWEEN 10000 AND 21000;

SELECT *
FROM fauna
WHERE discovery_date IS null;

SELECT *
FROM fauna
WHERE discovery_date < '1950-01-01';