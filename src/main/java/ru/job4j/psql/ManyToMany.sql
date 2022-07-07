create table students (
    id serial primary key,
    name varchar(255)
);

insert into students (name) values ('Artem');
insert into students (name) values ('Petr');
insert into students (name) values ('Maxim');

select * from students;

create table faculties (
    id serial primary key,
    faculty_name varchar(255)
);

insert into faculties (faculty_name) values ('Computer_since');
insert into faculties (faculty_name) values ('Bilogy');
insert into faculties (faculty_name) values ('Mathematics');

select * from faculties;

create table students_faculties(
    id serial primary key,
    student_id int references students(id),
    faculty_id int references faculties(id)
);

insert into students_faculties (student_id, faculty_id) values (1, 1);
insert into students_faculties (student_id, faculty_id) values (2, 3);
insert into students_faculties (student_id, faculty_id) values (3, 1);

select * from students_faculties;