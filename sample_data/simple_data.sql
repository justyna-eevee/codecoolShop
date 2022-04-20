-- create suppliers
INSERT INTO supplier (name) VALUES ('Justyna');
INSERT INTO supplier (name) VALUES ('Monika');

-- create users
INSERT INTO ShopUser (name) VALUES ('Zbigniew');
INSERT INTO ShopUser (name) VALUES ('Mikolaj');

-- create baskets for users
INSERT INTO basket (userId) VALUES (1);
INSERT INTO basket (userId) VALUES (2);

-- create categories
INSERT INTO category (name) VALUES ('food');
INSERT INTO category (name) VALUES ('electronics');

-- create products
INSERT INTO product (name, description, price, currency, supplierId, categoryId)
VALUES ('apple', 'sweet and red', 1.50, 'PLN', 1, 1);
INSERT INTO product (name, description, price, currency, supplierId, categoryId)
VALUES ('tomato', 'red', 3.40, 'PLN', 2, 1);

-- add products to baskets
INSERT INTO productForBasket (productId, basketId) VALUES (1, 1);
INSERT INTO productForBasket (productId, basketId) VALUES (2, 1);
INSERT INTO productForBasket (productId, basketId) VALUES (2, 2);