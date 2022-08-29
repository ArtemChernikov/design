CREATE VIEW show_most_number_of_books_by_author_where_order_active_false AS
SELECT authors.name, COUNT(author_id) AS total_books, orders.active
FROM books
         JOIN authors ON books.author_id = authors.id
         JOIN orders ON books.id = orders.book_id
WHERE orders.active = false
GROUP BY authors.name, orders.active
ORDER BY total_books DESC