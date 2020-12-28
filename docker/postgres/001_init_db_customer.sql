\c db_customer;
create table t_customer (
   uuid uuid NOT NULL,
   user_name varchar(200),
   hash_password varchar(100),
   first_name varchar(100),
   last_name varchar(100),
   email varchar(100),
   mobile varchar(20),
   facebook_id varchar(200),
   facebook_token varchar(500),
   user_type varchar(100),
   is_active boolean DEFAULT false,
   address text,
   created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   PRIMARY KEY (uuid)
);
INSERT INTO t_customer(uuid, user_name, hash_password, first_name, last_name, email, mobile, user_type, is_active, facebook_id, facebook_token,address)
VALUES ('a518d032-45f3-11eb-b378-0242ac130002', 'dattran', 'e10adc3949ba59abbe56e057f20f883e',
'dat', 'tran', 'trhoangdat@gmail.com', '0393974369','CUSTOMER', true, 'trhoangdat@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '801 Nguyen Van Linh, Quan 7, TP HCM');

INSERT INTO t_customer(uuid, user_name, hash_password, first_name, last_name, email, mobile, user_type, is_active, facebook_id, facebook_token, address)
VALUES ('299626c0-45f4-11eb-b378-0242ac130002', 'anthony', 'e10adc3949ba59abbe56e057f20f883e',
'Le', 'Quan', 'anthony@gmail.com', '0393974888','CUSTOMER', true, 'trhoangdat@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '801 Nguyen Van Linh, Quan 7, TP HCM');
