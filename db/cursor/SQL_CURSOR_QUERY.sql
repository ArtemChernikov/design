/*
1. Организовать обратный проход в курсоре (от 20 до 1 записи). Используйте в своем курсоре опцию SCROLL,
двигайтесь по курсору с помощью MOVE, данные получайте с помощью FETCH.
Последовательность команд определяйте произвольно
*/
BEGIN;
DECLARE cursor_products SCROLL CURSOR
FOR SELECT * FROM products;

MOVE LAST FROM cursor_products;
MOVE BACKWARD 1 FROM cursor_products;
FETCH FORWARD FROM cursor_products;

MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;

CLOSE cursor_products;
COMMIT;