BEGIN
TRANSACTION ISOLATION LEVEL REPEATABLE READ;

SAVEPOINT empty_table;

INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50),
       ('product_2', 'producer_2', 13, 100),
       ('product_3', 'producer_3', 23, 256),
       ('product_4', 'producer_1', 53, 34),
       ('product_5', 'producer_3', 103, 11),
       ('product_6', 'producer_3', 33, 12);

SELECT *
FROM products*;

ROLLBACK TO empty_table;

INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 100, 50),
       ('product_2', 'producer_2', 13, 100),
       ('product_3', 'producer_3', 23, 256),
       ('product_4', 'producer_1', 99, 34),
       ('product_5', 'producer_3', 103, 11),
       ('product_6', 'producer_3', 231, 22);

COMMIT;