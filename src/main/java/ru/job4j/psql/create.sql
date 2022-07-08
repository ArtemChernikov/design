create table roles(
    id serial primary key,
    role_name varchar(50)
);

create table rules(
    id serial primary key,
    rule text
);

create table users(
    id serial primary key,
    first_name varchar(25),
    last_name varchar(25),
    role_id int references roles(id)
);

create table role_rules(
    id serial primary key,
    role_id int references roles(id),
    rules_id int references rules(id)
);

create table categories(
    id serial primary key,
    category text
);

create table states(
    id serial primary key,
    state varchar(50)
);

create table items(
    id serial primary key,
    item_name varchar(255),
    user_id int references users(id),
    category_id int references categories(id),
    state_id int references states(id)
);

create table comments(
    id serial primary key,
    comment text,
    item_id int references items(id)
);

create table attachs(
    id serial primary key,
    attach text,
    item_id int references items(id)
);