create table orders(
    id bigserial primary key,
    username varchar(255) not null,
    total_cost int not null,
    address varchar,
    phone varchar(255),
    created_at timestamp,
    updated_at timestamp
);

create table order_items(
                            id bigserial primary key,
                            product_name varchar(255) not null,
                            quantity int not null,
                            order_id bigint not null references orders(id),
                            cost_per_product int not null,
                            cost int not null,
                            created_at timestamp,
                            updated_at timestamp
);

insert into orders (username, total_cost, address, phone)
values ('bob', 200, 'address', '12345');

insert into order_items (product_name, order_id, quantity, cost_per_product, cost)
values ('Bread', 1, 2, 100, 200);

