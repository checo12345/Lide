DROP TABLE IF EXISTS products;
CREATE TABLE products 
	( 
		coverage_area_id BIGINT NOT NULL,
		store_id BIGINT NOT NULL,
		product_id BIGINT NOT NULL AUTO_INCREMENT, 
		name VARCHAR(100) NOT NULL,
		description VARCHAR(300) NOT NULL,
		keywords VARCHAR(300) NOT NULL,
		categories VARCHAR(300) NOT NULL,
		avaible BOOLEAN NOT NULL,
		price DECIMAL(7,2) NOT NULL,
		last_update TIMESTAMP NOT NULL,
		image VARCHAR(100) NOT NULL,
		FULLTEXT (keywords),
		FULLTEXT (categories),
	    PRIMARY KEY (product_id)
	);


