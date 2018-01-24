cd /home/razp/Dimension/java/eclipse/workspace/LIDE_Beta/LIDE_BETA_SPI/beta;
mvn appengine:endpoints_get_discovery_doc;
mvn appengine:endpoints_get_client_lib;
cd /home/razp/Dimension/java/eclipse/workspace/LIDE_Beta/LIDE_BETA_SPI/beta/target/endpoints-client-libs/customerApi;
mvn install;

cd /home/razp/Dimension/java/eclipse/workspace/LIDE_Beta/LIDE_BETA_SPI/beta/target/endpoints-client-libs/customerApi/target;
cp customerApi-v1-1.22.0-SNAPSHOT.jar /home/razp/Dimension/java/android/workspace/CUSTOMER/app/libs;

cd /home/razp/Dimension/java/eclipse/workspace/LIDE_Beta/LIDE_BETA_SPI/beta/target/endpoints-client-libs/deliverApi/target;
cp deliverApi-v1-1.22.0-SNAPSHOT.jar /home/razp/Dimension/java/android/workspace/DELIVERY_MAN/app/libs;
cd /home/razp/Dimension/java/eclipse/workspace/LIDE_Beta/LIDE_BETA_SPI/beta/target/endpoints-client-libs/customerApi/target;
cp customerApi-v1-1.22.0-SNAPSHOT.jar /home/razp/Dimension/java/android/workspace/DELIVERY_MAN/app/libs;

cd /home/razp/Dimension/java/eclipse/workspace/LIDE_Beta/LIDE_BETA_SPI/beta/target/endpoints-client-libs/deliverApi/target;
cp deliverApi-v1-1.22.0-SNAPSHOT.jar /home/razp/Dimension/java/android/workspace/MANAGER/app/libs;
cd /home/razp/Dimension/java/eclipse/workspace/LIDE_Beta/LIDE_BETA_SPI/beta/target/endpoints-client-libs/adminApi/target;
cp adminApi-v1-1.22.0-SNAPSHOT.jar /home/razp/Dimension/java/android/workspace/MANAGER/app/libs;

