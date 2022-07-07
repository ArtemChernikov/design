create table employees (
    id serial primary key,
    name varchar(255),
    age integer,
    city text
);
insert into employees (name, age, city) values ('Artem', 24, 'Yaroslavl');
update employees set city = 'Moscow';
delete from employees;