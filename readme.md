This is a Rest API for Rewards Program assignment by Charter 
Author: Naveen Mangu


PreRequiste for Running this Program:
INTELLIJ OR ECLIPSE
Run CodingAssignmentApplication.class

Once program started

Program uses h2 in memory database, Once Program starts Please navigate to browser

http://localhost:8080/h2-console and add JDBC URL as jdbc:h2:mem:naveenmangu and connect
Once connected
Run the below script
CREATE TABLE SALE 
( 
AMOUNT INT NOT NULL, 
SALE_DT DATE NOT NULL, 
CUST_ID VARCHAR2(10) NOT NULL 
);

once it is done, we can use postman to test all the endpoints

It is done, the least time possible, Logging and unit testing is not done
