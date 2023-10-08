CREATE TABLE orders
(
    order_number BIGINT AUTO_INCREMENT PRIMARY KEY,
    type         VARCHAR(30)    NOT NULL,
    size         VARCHAR(10)    NOT NULL,
    milk         VARCHAR(20),
    delivery     VARCHAR(10)    NOT NULL,
    price        DECIMAL(10, 2) NOT NULL
);

CREATE TABLE status_order
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id  BIGINT NOT NULL ,
    status    VARCHAR(20) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (order_number)
)
