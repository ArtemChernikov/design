INSERT INTO students (name) VALUES ('Иван Иванов');
INSERT INTO students (name) VALUES ('Петр Петров');

INSERT INTO authors (name) VALUES ('Александр Пушкин');
INSERT INTO authors (name) VALUES ('Николай Гоголь');

INSERT INTO books (name, author_id) VALUES ('Евгений Онегин', 1);
INSERT INTO books (name, author_id) VALUES ('Капитанская дочка', 1);
INSERT INTO books (name, author_id) VALUES ('Дубровский', 1);
INSERT INTO books (name, author_id) VALUES ('Мертвые души', 2);
INSERT INTO books (name, author_id) VALUES ('Вий', 2);

INSERT INTO orders (active, book_id, student_id) VALUES (false, 1, 1);
INSERT INTO orders (active, book_id, student_id) VALUES (false, 3, 1);
INSERT INTO orders (book_id, student_id) VALUES (5, 2);
INSERT INTO orders (book_id, student_id) VALUES (4, 1);
INSERT INTO orders (book_id, student_id) VALUES (2, 2);