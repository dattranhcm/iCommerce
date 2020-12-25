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
create table t_order_items (
    id SERIAL NOT NULL,
    order_uuid uuid REFERENCES t_order(uuid),
    item_uuid uuid,
    item_price decimal,
    sub_order_amount decimal,
    sub_order_status varchar(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);
