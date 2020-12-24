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
    uuid uuid NOT NULL,
    product_name varchar(200),
    product_code varchar(50),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (uuid)
);
create table t_order_items (
    id SERIAL NOT NULL,
    order_uuid uuid REFERENCES t_order(uuid),
    item_uuid uuid REFERENCES t_products(uuid),
    item_price decimal,
    sub_order_amount decimal,
    sub_order_status varchar(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);
create table t_product_price (
    id SERIAL NOT NULL,
    product_uuid uuid REFERENCES t_products(uuid),
    price decimal,
    is_current_price boolean,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);
INSERT INTO t_products (uuid, product_name, product_code)
VALUES ('90733264-45f1-11eb-b378-0242ac130002', 'IPhone 12 mini', 'IP12MN');
INSERT INTO t_products (uuid, product_name, product_code)
VALUES ('ed8fcf20-45f1-11eb-b378-0242ac130002', 'IPhone 12 Blue', 'IP12BLU');
INSERT INTO t_products (uuid, product_name, product_code)
VALUES ('fdbcea9a-45f1-11eb-b378-0242ac130002', 'IPhone 12 Gold', 'IP12GOL');
INSERT INTO t_products (uuid, product_name, product_code)
VALUES ('08c758c6-45f2-11eb-b378-0242ac130002', 'IPhone 12 Pro Dark', 'IP12PRODAK');
INSERT INTO t_products (uuid, product_name, product_code)
VALUES ('1768c572-45f2-11eb-b378-0242ac130002', 'IPhone 12 Pro Max Gold', 'IP12PRMGOL');

INSERT INTO t_product_price (product_uuid, price, is_current_price)
VALUES ('90733264-45f1-11eb-b378-0242ac130002', '19000000', false);
INSERT INTO t_product_price (product_uuid, price, is_current_price)
VALUES ('90733264-45f1-11eb-b378-0242ac130002', '22000000', true);
INSERT INTO t_product_price (product_uuid, price, is_current_price)
VALUES ('ed8fcf20-45f1-11eb-b378-0242ac130002', '24000000', true);
INSERT INTO t_product_price (product_uuid, price, is_current_price)
VALUES ('fdbcea9a-45f1-11eb-b378-0242ac130002', '25000000', true);
INSERT INTO t_product_price (product_uuid, price, is_current_price)
VALUES ('08c758c6-45f2-11eb-b378-0242ac130002', '28000000', true);
INSERT INTO t_product_price (product_uuid, price, is_current_price)
VALUES ('1768c572-45f2-11eb-b378-0242ac130002', '35000000', true);