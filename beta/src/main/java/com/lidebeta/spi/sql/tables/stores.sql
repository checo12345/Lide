DROP TABLE IF EXISTS stores;
CREATE TABLE stores 
	( 
		coverage_area_id INT NOT NULL,
		store_id INT NOT NULL AUTO_INCREMENT,
		store_type_id INT NOT NULL,
		name VARCHAR(100) NOT NULL,
		description VARCHAR(300) NOT NULL,
		FOREIGN KEY (coverage_area_id) REFERENCES coverage_areas(coverage_area_id)
	    FOREIGN KEY (store_type_id) REFERENCES store_types(store_type_id)
		PRIMARY KEY (coverage_area_id, store_id)
	);
	
	
CREATE TABLE product_queried ( _id INTEGER NOT NULL,  FOREIGN KEY (_id) REFERENCES product (product._id);

CREATE TABLE product_selected ( 
		_id INTEGER NOT NULL PRIMARY KEY, 
		product_quantity INTEGER NOT NULL,  
		FOREIGN KEY (_id) REFERENCES product (_id),  
		UNIQUE ( _id) ON CONFLICT REPLACE
		);
		
		
		