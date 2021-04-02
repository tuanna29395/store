create table category
(
    id          int auto_increment
        primary key,
    name        varchar(255)                        null,
    created_at  timestamp default CURRENT_TIMESTAMP null,
    updated_at  timestamp default CURRENT_TIMESTAMP null,
    delete_flag int(4)                              null,
    parent_id   int                                 null,
    is_enabled  int(4)                              null,
    status      int(4)                              null
);

create table discount
(
    id          int auto_increment
        primary key,
    percent     int                                 null,
    start_at    timestamp                           null,
    end_at      timestamp                           null,
    description longtext                            null,
    created_at  timestamp default CURRENT_TIMESTAMP null,
    delete_flag int(4)                              null,
    updated_at  timestamp default CURRENT_TIMESTAMP null
);

create table order_items
(
    order_id         int                                 not null,
    product_id       int                                 not null,
    quantity         int                                 null,
    sold_price       decimal                             null,
    size_id          int                                 null,
    size_price       decimal   default 0                 null,
    updated_at       timestamp default CURRENT_TIMESTAMP null,
    deleted_at       timestamp                           null,
    created_at       timestamp default CURRENT_TIMESTAMP null,
    amount           decimal                             null,
    percent_discount double                              null,
    primary key (order_id, product_id)
);

create table orders
(
    id            int auto_increment
        primary key,
    user_id       int                                 null,
    payment       varchar(128)                        null,
    order_name    varchar(255)                        null,
    order_phone   varchar(20)                         null,
    order_address varchar(255)                        null,
    status        int(4)                              null,
    created_at    timestamp default CURRENT_TIMESTAMP null,
    updated_at    timestamp default CURRENT_TIMESTAMP null
);

create table password_reset_token
(
    id          int auto_increment
        primary key,
    token       varchar(255)                        null,
    user_id     int                                 null,
    expiry_date timestamp                           null,
    created_at  timestamp default CURRENT_TIMESTAMP null,
    updated_at  timestamp                           null
);

create table product
(
    id             int auto_increment
        primary key,
    name           varchar(255)                        null,
    image_url      varchar(254)                        null,
    original_price decimal                             null,
    sale_price     decimal                             null,
    created_at     timestamp default CURRENT_TIMESTAMP null,
    updated_at     timestamp default CURRENT_TIMESTAMP null,
    delete_flag    int(4)                              null,
    discount_id    int                                 null,
    category_id    int                                 null,
    description    longtext                            null,
    status         int(4)                              null
);

create table review
(
    id             int auto_increment
        primary key,
    user_id        int                                 null,
    product_id     int                                 null,
    review_content longtext                            null,
    number_star    int                                 null,
    created_at     timestamp default CURRENT_TIMESTAMP null
);

create table role
(
    id          int auto_increment
        primary key,
    name        varchar(255)                        null,
    created_at  timestamp default CURRENT_TIMESTAMP null,
    updated_at  timestamp default CURRENT_TIMESTAMP null,
    delete_flag int(4)                              null
);

create table size
(
    id    int auto_increment
        primary key,
    name  varchar(20) null,
    price decimal     null
);

create table user
(
    id           int auto_increment
        primary key,
    email        varchar(256)                        null,
    password     varchar(256)                        null,
    username     varchar(256)                        null,
    role_id      int                                 null,
    created_at   timestamp default CURRENT_TIMESTAMP null,
    updated_at   timestamp default CURRENT_TIMESTAMP null,
    delete_at    timestamp                           null,
    deleted_flag int(4)                              null,
    full_name    varchar(50)                         null,
    address      varchar(50)                         null,
    phone_number varchar(20)                         null,
    avatar       varchar(256)                        null,
    type_login   int(4)                              null,
    user_app_id  varchar(50)                         null
);

