CREATE TABLE payments (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    value decimal(19,2) NOT NULL,
    name varchar(255) NOT NULL,
    number varchar(16) NOT NULL,
    expiration varchar(7) NOT NULL,
    code varchar(3) NOT NULL,
    status varchar(255) NOT NULL,
    payment_form_id bigint(20) NOT NULL,
    order_id bigint(20) NOT NULL,
    PRIMARY KEY (id)
);