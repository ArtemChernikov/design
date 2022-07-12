--1. Создать таблицы и заполнить их начальными данными
CREATE TABLE departments(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE employees(
    id serial PRIMARY KEY,
    name varchar(255),
    department_id int REFERENCES departments(id)
);

INSERT INTO departments(name)
VALUES
('management'),
('control'),
('accountant'),
('IT'),
('cleaning'),
('financial'),
('security');

INSERT INTO employees(name, department_id)
VALUES
('Artem', 4),
('Evgeniy', null),
('Maxim', 5),
('Katya', 1),
('Valeria', 6),
('Alexander', null),
('Vladimir', 2),
('Nadezhda', 3),
('Vadim', 4),
('Viktor', 4);
--2. Выполнить запросы с left, rigth, full, cross соединениями
SELECT e.name, d.name
FROM employees e
LEFT JOIN departments d ON department_id = d.id;

SELECT e.name, d.name
FROM employees e
RIGHT JOIN departments d ON department_id = d.id;

SELECT e.name, d.name
FROM employees e
FULL JOIN departments d ON department_id = d.id;

SELECT e.name, d.name
FROM employees e
CROSS JOIN departments d;
--3. Используя left join найти департаменты, у которых нет работников
SELECT d.name
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id
WHERE e.department_id IS null;
--4. Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).
SELECT e.name, d.name
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id;

SELECT e.name, d.name
FROM employees e
RIGHT JOIN departments d ON e.department_id = d.id
--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
CREATE TABLE teens(
id serial PRIMARY KEY,
    name varchar (50),
    gender varchar (20)
);

INSERT INTO teens(name, gender)
VALUES
('Fedya', 'male'),
('Pasha', 'male'),
('Borya', 'male'),
('Misha', 'male'),
('Artem', 'male'),
('Maxim', 'male'),
('Katya', 'female'),
('Masha', 'female'),
('Vika', 'female'),
('Marina', 'female'),
('Nadya', 'female'),
('Valeria', 'female'),
('Sasha', 'female');

SELECT t1.name, t1.gender, t2.name, t2.gender
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender != t2.gender;