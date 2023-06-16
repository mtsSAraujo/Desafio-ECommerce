CREATE TABLE products(
id int not null AUTO_INCREMENT,
product_name varchar(40),
product_price double(6,2),
quantity int,
PRIMARY KEY (id)
);

CREATE TABLE Kart(
id int not null AUTO_INCREMENT,
product_name varchar(40),
product_price double(6,2),
product_quantity int,
fk_products int,
PRIMARY KEY (id),
CONSTRAINT product_if_fk Foreign KEY (fk_products) REFERENCES products (id)
);

INSERT INTO products (product_name, product_price, quantity)
VALUES
    ('iPhone 13 Pro', 1299.00, 10),
    ('Samsung Galaxy S21', 999.99, 5),
    ('Sony PlayStation 5', 499.99, 20),
    ('Apple AirPods Pro', 249.00, 15),
    ('Nintendo Switch', 299.99, 8),
    ('Dell XPS 13 Laptop', 1199.00, 3),
    ('Canon EOS Rebel T7i', 699.99, 12),
    ('Sony WH-1000XM4 Headphones', 349.99, 6),
    ('Fitbit Versa 3', 199.95, 9),
    ('LG 55" OLED TV', 1499.99, 2),
    ('Bose QuietComfort 35 II', 299.00, 7),
    ('Microsoft Surface Pro 7', 899.99, 4),
    ('GoPro HERO9 Black', 449.99, 11),
    ('Amazon Echo Dot (4th Gen)', 49.99, 18),
    ('Samsung 65" QLED TV', 1999.00, 1),
    ('Logitech MX Master 3 Mouse', 99.99, 14),
    ('Google Pixel 6', 799.00, 7),
    ('Nintendo Switch Pro Controller', 69.99, 10),
    ('Microsoft Xbox Series X', 499.99, 5),
    ('Bose SoundLink Revolve+', 299.00, 6),
    ('Apple Watch Series 7', 399.00, 8),
    ('Sony A7 III', 1999.99, 3),
    ('HP Spectre x360 Laptop', 1299.99, 4),
    ('Fitbit Charge 4', 149.95, 15),
    ('Sony WH-1000XM3 Headphones', 299.99, 9),
    ('Canon EOS R6', 2499.99, 2),
    ('JBL Flip 5', 119.95, 20),
    ('Samsung Galaxy Watch 4', 249.99, 7),
    ('LG 27" 4K Monitor', 399.99, 6),
    ('Apple MacBook Pro', 1999.00, 3);
