\c dborder;
create table t_orders (
   id SERIAL PRIMARY KEY NOT NULL,
   first_name TEXT NOT NULL,
   last_name TEXT NOT NULL,
   email TEXT NOT NULL,
   mobile TEXT NOT NULL,
   created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
INSERT INTO t_orders (first_name,last_name,email,mobile) values ('MR', 'MERRY CHRISMAS', 'hoangdat89@gmail.com', '0393974369');
