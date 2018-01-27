SELECT product._id, product_name, product_image, product_price, product_quantity 
FROM product INNER JOIN product_selected ON product._id = product_selected._id ;
--WHERE (_coverage_area_id= (select _id from selected_coverage_area) AND product_quantity>0) ORDER BY product_name ASC;