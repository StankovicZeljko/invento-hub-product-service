CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price NUMERIC(10,2)
);

INSERT INTO product (name, price) VALUES ('Product 1', 19.99);
INSERT INTO product (name, price) VALUES ('Product 2', 29.99);
INSERT INTO product (name, price) VALUES ('Product 3', 39.99);
INSERT INTO product (name, price) VALUES ('Product 4', 19.99);
INSERT INTO product (name, price) VALUES ('Product 5', 29.99);
INSERT INTO product (name, price) VALUES ('Product 6', 39.99);
