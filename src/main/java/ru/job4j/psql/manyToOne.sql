create table faculty(
    id serial primary key,
    faculty_name varchar(255)
);
insert into faculty (faculty_name) values('ComputerSince');
insert into faculty (faculty_name) values('Boilogy');
select * from faculty;
create table students (
    id serial primary key,
    name varchar(255),
    faculty_id int references faculty(id)
);

insert into students(name, faculty_id) values ('Artem', 1);
insert into students(name, faculty_id) values ('Maxim', 2);
select * from students;