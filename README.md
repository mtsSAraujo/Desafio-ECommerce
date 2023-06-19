# ECommerce Challenge
Used Technologies
The project was developed using Java (version 20.0.1) with a database connection (MySQL) through JDBC.


## About the project
The project consists of a shopping cart implemented using JAVA and a database tool (MySQL or MongoDB).


In this project, everything happens through the IDE terminal, so in order to interact, the user needs to follow the steps specified in the prompt.

## How to run the program
Clone the repository:

git clone: https://github.com/mtsSAraujo/Desafio-ECommerce.git

In case of an error while populating the database, it is necessary for the user to access the "db.properties" file and change the name of the database to avoid conflicts with any existing database.
The "db.properties" file has the following format:

user = default

password = 12345678

dburl=jdbc:mysql://localhost:3306/products

createDatabaseIfNotExist=true

useSSL=false

--------------------------------------------------------------------------------------

If necessary, the user should modify the field:

"dburl=jdbc:mysql://localhost:3306/products";

Where "products" refers to the name of the database.

Thus, the new name will be:

"dburl=jdbc:mysql://localhost:3306/(chosen name)";

Additionally, a connection to MySQL should be created, and if necessary, the "user" and "password" fields should be modified to match those of your own MySQL.

The program contains all the logic in its main method to create and populate the database upon initialization. The user only needs to start MySQL and execute the program from the main method.
