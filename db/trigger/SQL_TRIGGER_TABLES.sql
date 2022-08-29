CREATE TABLE products (
    id serial PRIMARY KEY,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

CREATE TABLE history_of_price (
    id serial PRIMARY KEY ,
    name varchar(50),
    price integer,
    date timestamp
);