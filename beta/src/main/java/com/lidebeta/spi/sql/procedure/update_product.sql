DROP PROCEDURE IF EXISTS update_product;
DELIMITER //
CREATE PROCEDURE update_product(
									 IN _product_id INT,
									 IN _name VARCHAR(100),
									 IN _description VARCHAR(300),
									 IN _keywords VARCHAR(300),
									 IN _categories VARCHAR(300),
									 IN _avaible BOOLEAN,
									 IN _price DECIMAL(7,2),
									 IN _image VARCHAR(100)
								)
 BEGIN
	UPDATE products
		SET 
				name=_name,
				description=_description,
				keywords=_keywords,
				categories=_categories,
				avaible=_avaible,
				price=_price,
				last_update=NOW(),
				image=_image
		WHERE
				product_id=_product_id;
 END//
 
 
 --call update_product(2,'name','description','keywords','categories',TRUE, 12.5, 'img');
 --call insert_product('name','description','keywords','categories',TRUE, 12.5, 'img');