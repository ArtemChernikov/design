/*
1. Триггер должен срабатывать после вставки данных, для любого товара и
   просто насчитывать налог на товар. Действовать он должен не на каждый ряд,
   а на запрос (statement уровень).
*/
CREATE
OR REPLACE FUNCTION plus_tax()
RETURNS TRIGGER AS
$$
BEGIN
UPDATE products
SET price = price + price * 0.05
WHERE id = (SELECT id FROM inserted);
RETURN NEW;
END;
 $$
LANGUAGE 'plpgsql';

CREATE TRIGGER plus_tax_trigger
    AFTER INSERT
    ON products
    REFERENCING new TABLE as
                    inserted
    FOR EACH STATEMENT
    EXECUTE PROCEDURE plus_tax();
/*
2. Триггер должен срабатывать до вставки данных и высчитывать налог на товар. Используем row уровень.
*/
CREATE
OR replace FUNCTION minus_tax()
RETURNS TRIGGER AS
$$
BEGIN
NEW.price = NEW.price - NEW.price * 0.05;
RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER minus_tax_trigger
    BEFORE INSERT
    ON products
    FOR EACH ROW
    EXECUTE procedure minus_tax();
/*
3. Написать триггер на row уровне, который при вставке продукта в таблицу products,
   будет заносить имя, цену и текущую дату в таблицу history_of_price.
*/
CREATE
OR replace FUNCTION add()
RETURNS TRIGGER AS
$$
BEGIN
INSERT INTO history_of_price (name, price, date)
VALUES (NEW.name, NEW.price, current_timestamp);
RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER add_history
    AFTER INSERT
    ON products
    FOR EACH ROW
    EXECUTE procedure add();