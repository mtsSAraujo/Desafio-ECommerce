CREATE TABLE Kart(
id int not null AUTO_INCREMENT,
product_name varchar(40),
product_price double(6,2),
product_quantity int,
fk_products int,
PRIMARY KEY (id),
CONSTRAINT product_if_fk Foreign KEY (fk_products) REFERENCES products (id)
);