-- create a database call supermarket
drop database if exists supermarket;

create database supermarket;
use supermarket;

create table promo_items(
    item_id int not null auto_increment,
    item_name varchar(64) not null,
    item_price varchar(64) not null,
    item_unit varchar(64) not null,
    date timestamp not null default current_timestamp,
    primary key(item_id)
);

create table search_item(
    item_type varchar(64) not null,
    item_name varchar(64) not null,
    item_name_unit varchar(256) not null,
    primary key (item_name_unit)
    -- 1st foreign key refers to the line_item table
    -- constraint fk_item_name
    --     foreign key(item_name)
    -- references refers to the one in order table
        -- references user_list(item_name)
);

create table user_list(
    user_id int not null auto_increment,
    name varchar(64),
    email varchar(256) not null,
    item_name varchar(64) not null,
    item_price varchar(64) not null,
    item_unit varchar(64) not null,
    item_name_unit varchar(256) not null,
    primary key(user_id),
    constraint fk_item_name foreign key(item_name_unit)
    references search_item(item_name_unit)
);

