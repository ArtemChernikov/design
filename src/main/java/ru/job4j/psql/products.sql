CREATE TABLE type(
    id serial PRIMARY KEY,
    name varchar(30)
);

CREATE TABLE product(
    id serial PRIMARY KEY,
    name varchar(30),
    type_id int REFERENCES type(id),
    expired_date date,
    price float
);

INSERT INTO type (name)
VALUES
('Вино'),
('Водка'),
('Молоко'),
('Сыр'),
('Шоколад'),
('Мороженое');

INSERT INTO product(name, type_id, expired_date, price)
VALUES
('Киндзмараули', 1, '2025-05-12', 899.99),
('Апсны', 1, '2025-07-02', 599.99),
('Лютый медведь', 2, '2125-01-01', 299.99),
('Путинка', 2, '2035-05-11', 499.99),
('Домик в деревне', 3, '2022-07-25', 74.99),
('Веселая корова', 3, '2022-08-11', 55.99),
('Вятский', 4, '2022-08-15', 256.99),
('Гауда', 4, '2022-08-25', 345.99),
('Российский', 4, '2022-06-25', 345.99),
('Аленка', 5, '2022-12-11', 89.99),
('Красный октябрь', 5, '2023-01-07', 69.99),
('Пломбир', 6, '2022-08-07', 69.99),
('Рожок', 6, '2022-07-06', 69.99),
('Смоленское мороженое', 6, '2022-07-25', 53.99),
('Мороженое нежное', 6, '2022-07-21', 69.99);

SELECT *
FROM product
JOIN type ON type_id = type.id
WHERE type.name LIKE 'Сыр';

SELECT *
FROM product
WHERE name LIKE '%мороженое' OR name LIKE 'Мороженое%';

SELECT *
FROM product
WHERE expired_date < current_date;

SELECT DISTINCT MAX(price)
FROM product;

SELECT type.name, COUNT(*)
FROM product
JOIN type ON type_id = type.id
GROUP BY type.name;

SELECT *
FROM product
JOIN type ON type_id = type.id
WHERE type.name LIKE 'Сыр' OR type.name LIKE 'Молоко';

SELECT type.name, COUNT(*)
FROM type
JOIN product ON type.id = type_id
GROUP BY type.name
HAVING COUNT(*) < 10;

SELECT product.name, type.name
FROM product
JOIN type ON type.id = type_id;