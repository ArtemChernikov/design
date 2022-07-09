CREATE TABLE roles(
    id serial PRIMARY KEY,
    role_name varchar(50)
);

CREATE TABLE rules(
    id serial PRIMARY KEY,
    rule text
);

CREATE TABLE users(
    id serial PRIMARY KEY,
    first_name varchar(25),
    last_name varchar(25),
    role_id int REFERENCES roles(id)
);

CREATE TABLE role_rules(
    id serial PRIMARY KEY,
    role_id int REFERENCES roles(id),
    rules_id int REFERENCES rules(id)
);

CREATE TABLE categories(
    id serial PRIMARY KEY,
    category text
);

CREATE TABLE states(
    id serial PRIMARY KEY,
    state varchar(50)
);

CREATE TABLE items(
    id serial PRIMARY KEY,
    item_name varchar(255),
    user_id int REFERENCES users(id),
    category_id int REFERENCES categories(id),
    state_id int REFERENCES states(id)
);

CREATE TABLE comments(
    id serial PRIMARY KEY,
    comment text,
    item_id int REFERENCES items(id)
);

CREATE TABLE attachs(
    id serial PRIMARY KEY,
    attach text,
    item_id int REFERENCES items(id)
);