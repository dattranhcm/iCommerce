\c db_order;
create table t_order (
   uuid uuid NOT NULL,
   customer_id uuid NOT NULL,
   total_amount decimal,
   ship_address TEXT NOT NULL,
   order_code varchar(50) NOT NULL,
   status varchar(100) NOT NULL,
   created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   PRIMARY KEY (uuid)
);
create table t_products (
    id SERIAL NOT NULL,
    product_name varchar(200),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);
create table t_order_items (
    id SERIAL NOT NULL,
    order_uuid uuid REFERENCES t_order(uuid),
    item_id int4 REFERENCES t_products(id),
    item_price decimal,
    sub_order_amount decimal,
    sub_order_status varchar(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);
create table t_product_price (
    id SERIAL NOT NULL,
    product_name varchar(200),
    product_id int4 REFERENCES t_products(id),
    price decimal,
    is_current_price boolean,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);