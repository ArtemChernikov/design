/*
1. Процедура на удаление строки по имени
*/
CREATE
OR REPLACE PROCEDURE delete_data_by_name (new_name varchar)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
DELETE
FROM products
WHERE name = new_name;
END;
$$;

/*
2. Функция на удаление строк, если id > 3
*/
CREATE
OR REPLACE FUNCTION delete_data_id_more_than_three()
RETURNS void
LANGUAGE 'plpgsql'
AS
$$
BEGIN
DELETE
FROM products
WHERE id > 3;
END;
$$;