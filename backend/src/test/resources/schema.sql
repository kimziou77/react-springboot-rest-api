CREATE TABLE product
(
    id          bigint primary key auto_increment,

    name        VARCHAR(20) not null,
    category    VARCHAR(50) not null,
    price       bigint      not null,
    quantity    bigint      not null,
    description VARCHAR(500) default null,
    image_path varchar(200) default null,

    created_at  datetime(6) not null,
    updated_at  datetime(6) not null
);

CREATE TABLE orders
(
    id           bigint primary key auto_increment,

    email        VARCHAR(50)  not null,
    address      VARCHAR(200) not null,
    total_price  bigint       not null,

    order_status VARCHAR(50)  not null,
    created_at   datetime(6)  not null,
    updated_at   datetime(6)  not null
);

CREATE TABLE order_product
(
    id          bigint primary key auto_increment,

    order_id    bigint      not null,
    product_id  bigint      not null,

    count       bigint      not null,
    total_price bigint      not null,

    created_at  datetime(6) not null,
    updated_at  datetime(6) not null,

    constraint fk_order_product_to_orders foreign key (order_id) references orders (id) on delete cascade,
    constraint fk_order_product_to_product foreign key (product_id) references product (id)
);
CREATE INDEX order_id_index ON order_product (order_id)