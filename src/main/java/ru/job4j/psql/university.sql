CREATE TABLE deans(
    dean_id serial PRIMARY KEY,
    dean_name varchar(20)
);

CREATE TABLE faculties(
    faculty_id serial PRIMARY KEY,
    faculty_name text,
    faculty_dean int REFERENCES deans(dean_id) UNIQUE
);

CREATE TABLE students(
    student_id serial PRIMARY KEY,
    first_name varchar(15),
    last_name varchar(15),
    dean_id int REFERENCES faculties(faculty_dean),
    faculty_id int REFERENCES faculties(faculty_id)
);

INSERT INTO deans (dean_name)
VALUES
('Petr_Ivanov'),
('Evgeniy_Sidorov'),
('Maxim_Vyatkin');

INSERT INTO faculties (faculty_name, faculty_dean)
VALUES
('Biology', 1),
('Chemistry', 3),
('Computer_since', 2);

INSERT INTO students (first_name, last_name, dean_id, faculty_id)
VALUES
('Artem', 'Chernikov', 2, 3),
('Andrey', 'Suhov', 3, 2),
('Fedor', 'Terentev', 1, 1);

SELECT first_name AS name, last_name AS surname, dean_name, faculty_name
FROM students
INNER JOIN faculties ON students.faculty_id = faculties.faculty_id
INNER JOIN deans ON students.dean_id = deans.dean_id
ORDER BY name;

SELECT faculty_name AS f_name, dean_name AS d_name
FROM faculties
INNER JOIN deans ON faculty_dean = deans.dean_id
ORDER BY f_name DESC;

SELECT deans.dean_id, dean_name, first_name AS student_name, last_name AS student_surname
FROM deans
INNER JOIN students ON deans.dean_id = students.dean_id;