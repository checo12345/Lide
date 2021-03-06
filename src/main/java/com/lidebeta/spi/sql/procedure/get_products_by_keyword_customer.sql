DROP PROCEDURE IF EXISTS get_products_by_keyword_customer;
DELIMITER //
CREATE PROCEDURE get_products_by_keyword_customer(IN _store_id BIGINT, IN keyword VARCHAR(100))
 BEGIN
	SELECT coverage_area_id, store_id, product_id, name, description, avaible, price, image	
	FROM products
	WHERE store_id = _store_id AND keywords LIKE concat('%', keyword,'%');
 END//