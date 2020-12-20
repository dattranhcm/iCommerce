\c db_order;
create table t_order (
   uuid uuid NOT NULL,
   customer_id uuid NOT NULL,
   total_amount integer,
   ship_address TEXT NOT NULL,
   order_code varchar(50) NOT NULL,
   status varchar(100) NOT NULL,
   created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   PRIMARY KEY (uuid)
);
create table t_order_items (
    id SERIAL NOT NULL,
    order_uuid uuid NOT NULL,
    item_uuid uuid NOT NULL,
    item_price integer,
    sub_order_amount integer,
    sub_order_status varchar(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);
