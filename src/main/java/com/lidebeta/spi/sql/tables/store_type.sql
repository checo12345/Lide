DROP TABLE IF EXISTS store_types;
CREATE TABLE stores_types 
	( 
		store_type_id INT NOT NULL AUTO_INCREMENT,
		image VARCHAR(100) NOT NULL,
	    PRIMARY KEY (store_type_id)
	);