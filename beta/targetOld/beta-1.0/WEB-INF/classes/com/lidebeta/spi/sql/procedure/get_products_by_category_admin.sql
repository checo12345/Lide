DROP PROCEDURE IF EXISTS get_products_by_category_admin;
DELIMITER //
CREATE PROCEDURE get_products_by_category_admin(IN category VARCHAR(100))
 BEGIN
	SELECT *
	FROM products
	WHERE categories LIKE concat('%', category,'%');
 END//