DROP PROCEDURE IF EXISTS insert_product;
DELIMITER //
CREATE PROCEDURE insert_product(
									 IN _name VARCHAR(100),
									 IN _description VARCHAR(300),
									 IN _keywords VARCHAR(300),
									 IN _categories VARCHAR(300),
									 IN _avaible BOOLEAN,
									 IN _price DECIMAL(7,2),
									 IN _image VARCHAR(100)
								)
 BEGIN
	INSERT 
		INTO products 
			(
				name,
				description,
				keywords,
				categories,
				avaible,
				price,
				last_update,
				image
			)
		VALUES 
			(	
				_name,
				_description,
				_keywords,
				_categories,
				_avaible,
				_price,
				NOW(),
				_image
			); 
 END//