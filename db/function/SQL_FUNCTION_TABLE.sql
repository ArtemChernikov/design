CREATE TABLE products
(
    id       serial PRIMARY KEY,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);