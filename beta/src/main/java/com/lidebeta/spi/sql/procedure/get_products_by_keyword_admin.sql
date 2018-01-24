DROP PROCEDURE IF EXISTS get_products_by_keyword_admin;
DELIMITER //
CREATE PROCEDURE get_products_by_keyword_admin(IN keyword VARCHAR(100))
 BEGIN
	SELECT *	
	FROM products
	WHERE keywords LIKE concat('%', keyword,'%');
 END//