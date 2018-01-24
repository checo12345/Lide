DROP TABLE IF EXISTS categories;
CREATE TABLE categories 
	( 
		name VARCHAR(100) NOT NULL,
		description VARCHAR(100) NOT NULL,
		image VARCHAR(100) NOT NULL,
	    PRIMARY KEY (name)
	);