CREATE TABLE students
(
    id   serial PRIMARY KEY,
    name varchar(50)
);

CREATE TABLE authors
(
    id   serial PRIMARY KEY,
    name varchar(50)
);

CREATE TABLE books
(
    id        serial PRIMARY KEY,
    name      varchar(200),
    author_id integer REFERENCES authors (id)
);

CREATE TABLE orders
(
    id         serial PRIMARY KEY,
    active     boolean DEFAULT true,
    book_id    integer REFERENCES books (id),
    student_id integer REFERENCES students (id)
);