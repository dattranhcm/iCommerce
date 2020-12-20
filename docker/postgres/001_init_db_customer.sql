\c db_customer;
create table t_customer (
   uuid uuid NOT NULL,
   user_name varchar(200) NOT NULL,
   hash_password varchar(100) NOT NULL,
   first_name varchar(100) NOT NULL,
   last_name varchar(100) NOT NULL,
   email varchar(100) NOT NULL,
   mobile varchar(20) NOT NULL,
   facebook_id varchar(200),
   facebook_token varchar(500),
   user_type varchar(100),
   is_active boolean DEFAULT false,
   created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   PRIMARY KEY (uuid)
);
create table t_address_customer (
   id SERIAL NOT NULL,
   address TEXT,
   customer_id uuid REFERENCES t_customer(uuid),
   is_default boolean,
   created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   PRIMARY KEY (id)
);