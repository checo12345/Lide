pass="-pc0ng0r3d"
base="/home/razp/Dimension/java/eclipse/workspace/LIDE_Beta/LB1/beta/src/main/java/com/lide/beta/products/sql/"


path=$base"tables/produts.sql"
mysql -u root $pass lide_beta < $path 
echo "products table reload"

path=$base"sampledata/load_sample_data.sql"
mysql -u root $pass lide_beta < $path 
echo "sample data reload"

path=$base"procedure/get_products_by_category_customer.sql"
mysql -u root $pass lide_beta < $path 
echo "get_products_by_kind_customer procedure reload"

path=$base"procedure/get_products_by_keyword_customer.sql"
mysql -u root $pass lide_beta < $path 
echo "get_products_by_keyword_customer procedure reload"

path=$base"procedure/insert_product.sql"
mysql -u root $pass lide_beta < $path 
echo "insert_product procedure reload"

path=$base"procedure/update_product.sql"
mysql -u root $pass lide_beta < $path 
echo "update_product procedure reload"

path=$base"procedure/get_products_by_category_admin.sql"
mysql -u root $pass lide_beta < $path 
echo "get_products_by_kind_admin procedure reload"

path=$base"procedure/get_products_by_keyword_admin.sql"
mysql -u root $pass lide_beta < $path 
echo "get_products_by_keyword_admin procedure reload"
