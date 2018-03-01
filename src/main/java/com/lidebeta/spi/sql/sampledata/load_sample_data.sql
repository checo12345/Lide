DELETE FROM products;
LOAD DATA LOCAL INFILE '/home/razp/Dimension/java/eclipse/workspace/LIDE_Beta/LIDE_BETA_SPI/beta/src/main/java/com/lidebeta/spi/sql/sampledata/products.csv' INTO TABLE products
FIELDS TERMINATED BY ','
(coverage_area_id, store_id, product_id, name, description, keywords, categories, avaible, price, last_update, image);




LOAD DATA LOCAL INFILE '/home/razp/Downloads/product.csv' INTO TABLE products FIELDS TERMINATED BY ',' (_coverage_area_id, _store_id, _id, product_name, product_description, product_price, product_avaible);