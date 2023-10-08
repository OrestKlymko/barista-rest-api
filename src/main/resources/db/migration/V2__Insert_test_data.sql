INSERT INTO orders (type, size, milk, delivery, price)
VALUES ('Espresso', 'Small', 'Regular', 'In Store', 2.99);

INSERT INTO orders (type, size, milk, delivery, price)
VALUES ('Latte', 'Medium', 'Almond', 'Delivery', 4.49);

INSERT INTO status_order (order_id, status)
VALUES (1, 'waiting');

INSERT INTO status_order (order_id, status)
VALUES (2, 'preparation');

