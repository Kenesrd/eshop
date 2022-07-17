-------USER---------
drop sequence if exists user_seq;
create sequence user_seq start 1 increment 1;

drop table if exists users cascade;
create table users
(
    id        int8    not null,
    archive   boolean not null,
    email     varchar(255),
    name      varchar(255),
    password  varchar(255),
    role      varchar(255),
    primary key (id)
);

----------BUCKET----------------
drop sequence if exists bucket_seq;
create sequence bucket_seq start 1 increment 1;

DROP TABLE IF EXISTS buckets CASCADE;
create table buckets
(
    id      int8 not null,
    user_id int8,
    primary key (id)
);

-----------LINK BETWEEN BUCKET AND USER---------
alter table if exists buckets
    add constraint buckets_fk_user
        foreign key (user_id) references users;

------------CATEGORY---------
drop sequence if exists category_seq;
create sequence category_seq start 1 increment 1;

DROP TABLE IF EXISTS categories CASCADE;
create table categories
(
    id    int8 not null,
    title varchar(255),
    primary key (id)
);

-----------PRODUCT----------
drop sequence if exists product_seq;
create sequence product_seq start 1 increment 1;

DROP TABLE IF EXISTS products CASCADE;
create table products
(
    id    int8 not null,
    price numeric(19, 2),
    title varchar(255),
    primary key (id)
);

-----------CATEGORY AND PRODUCT---------
DROP TABLE IF EXISTS product_categories CASCADE;
create table product_categories
(
    product_id  int8 not null,
    category_id int8 not null
);

alter table if exists product_categories
    add constraint products_categories_fk_category
        foreign key (category_id) references categories;
alter table if exists product_categories
    add constraint products_categories_fk_products
        foreign key (product_id) references products;

------------PRODUCT IN BUCKET-------------
DROP TABLE IF EXISTS backets_products CASCADE;
create table backet_products
(
    bucket_id  int8 not null,
    product_id int8 not null
);

alter table if exists backet_products
    add constraint buckets_products_fk_products
        foreign key (product_id) references products;
alter table if exists backet_products
    add constraint buckets_products_fk_buckets
        foreign key (bucket_id) references buckets;


----------------ORDERS-------------------
drop sequence if exists order_seq;
create sequence order_seq start 1 increment 1;

DROP TABLE IF EXISTS orders CASCADE;
create table orders
(
    id      int8 not null,
    address varchar(255),
    created timestamp,
    status  varchar(255),
    sum     numeric(19, 2),
    updated timestamp,
    user_id int8,
    primary key (id)
);

alter table if exists orders
    add constraint orders_fk_users
        foreign key (user_id) references users;

------------ORDER DETAILS---------------
drop sequence if exists order_details_seq;
create sequence order_details_seq start 1 increment 1;

DROP TABLE IF EXISTS order_details CASCADE;
create table order_details
(
    id         int8 not null,
    amount     numeric(19, 2),
    price      numeric(19, 2),
    order_id   int8,
    product_id int8,
    primary key (id)
);


alter table if exists order_details
    add constraint order_details_fk_orders
        foreign key (order_id) references orders;
alter table if exists order_details
    add constraint order_details_fk_products
        foreign key (product_id) references products;


-- ========================================
create table orders_details
(
    order_id   int8 not null,
    details_id int8 not null
);
alter table if exists orders_details
    add constraint unique_orders_details
        unique (details_id);
alter table if exists orders_details
    add constraint orders_details_fk_order_details
        foreign key (details_id) references order_details;
alter table if exists orders_details
    add constraint orders_details_fk_orders
        foreign key (order_id) references orders;



