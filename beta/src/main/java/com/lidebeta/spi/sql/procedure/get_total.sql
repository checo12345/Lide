DROP PROCEDURE IF EXISTS get_total;
DELIMITER //
CREATE PROCEDURE get_total(IN fruitArray VARCHAR(255))
 BEGIN
	SELECT coverage_area_id, store_id, product_id, name, description, avaible, price, image	
	FROM products
	WHERE store_id = _store_id AND keywords LIKE concat('%', keyword,'%');
 END//