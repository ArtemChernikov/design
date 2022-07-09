INSERT INTO roles (role_name)
VALUES
('root'),
('user'),
('guest');

INSERT INTO rules (rule)
VALUES
('r--'),
('rw-'),
('--c'),
('r-c'),
('-wc'),
('rwc');

INSERT INTO role_rules (role_id, rules_id)
VALUES
(1, 6),
(2, 5),
(3, 1);

INSERT INTO users (first_name, last_name, role_id)
VALUES
('Artem', 'Chernikov', 1),
('Petr', 'Sachnov', 3),
('Maxim', 'Cherepanov', 3),
('Katya', 'Shriber', 2);

INSERT INTO categories (category)
VALUES
('first'),
('second'),
('third');

INSERT INTO states (state)
VALUES
('complete'),
('inProcess'),
('rejected');

INSERT INTO items (item_name, user_id, category_id, state_id)
VALUES
('Delete', 1, 1, 2),
('Create', 2, 3, 3),
('Update', 4, 2, 1);

INSERT INTO comments (comment, item_id)
VALUES
('firstComment', 1),
('secondComment', 3),
('thirdComment', 2);

INSERT INTO attachs (attach, item_id)
VALUES
('firstFile', 1),
('secondFile', 2),
('thirdFile', 3);