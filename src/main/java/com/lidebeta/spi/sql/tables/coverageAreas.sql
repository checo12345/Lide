DROP TABLE IF EXISTS coverage_areas;
CREATE TABLE coverage_areas 
	( 
		coverage_area_id INT NOT NULL AUTO_INCREMENT, 
		name VARCHAR(100) NOT NULL,
		description VARCHAR(300) NOT NULL,
	    PRIMARY KEY (coverage_area_id)
	);