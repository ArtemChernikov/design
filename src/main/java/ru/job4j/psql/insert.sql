insert into roles (role_name) values ('root');
insert into roles (role_name) values ('user');
insert into roles (role_name) values ('guest');

insert into rules (rule) values ('r--');
insert into rules (rule) values ('rw-');
insert into rules (rule) values ('--c');
insert into rules (rule) values ('r-c');
insert into rules (rule) values ('-wc');
insert into rules (rule) values ('rwc');

insert into role_rules (role_id, rules_id) values (1, 6);
insert into role_rules (role_id, rules_id) values (2, 5);
insert into role_rules (role_id, rules_id) values (3, 1);

insert into users (first_name, last_name, role_id) values ('Artem', 'Chernikov', 1);
insert into users (first_name, last_name, role_id) values ('Petr', 'Sachnov', 3);
insert into users (first_name, last_name, role_id) values ('Maxim', 'Cherepanov', 3);
insert into users (first_name, last_name, role_id) values ('Katya', 'Shriber', 2);

insert into categories (category) values ('first');
insert into categories (category) values ('second');
insert into categories (category) values ('third');

insert into states (state) values ('complete');
insert into states (state) values ('inProcess');
insert into states (state) values ('rejected');

insert into items (item_name, user_id, category_id, state_id) values ('Delete', 1, 1, 2);
insert into items (item_name, user_id, category_id, state_id) values ('Create', 2, 3, 3);
insert into items (item_name, user_id, category_id, state_id) values ('Update', 4, 2, 1);

insert into comments (comment, item_id) values ('firstComment', 1);
insert into comments (comment, item_id) values ('secondComment', 3);
insert into comments (comment, item_id) values ('thirdComment', 2);

insert into attachs (attach, item_id) values ('firstFile', 1);
insert into attachs (attach, item_id) values ('secondFile', 2);
insert into attachs (attach, item_id) values ('thirdFile', 3);