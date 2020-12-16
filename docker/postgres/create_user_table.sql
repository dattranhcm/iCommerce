--DROP DATABASE IF EXISTS dbuser;
CREATE DATABASE IF NOT EXISTS dbuser;
--DROP TABLE IF EXISTS t_users CASCADE;
create table t_users (
   id SERIAL PRIMARY KEY NOT NULL,
   first_name TEXT NOT NULL,
   last_name TEXT NOT NULL,
   email TEXT NOT NULL,
   mobile TEXT NOT NULL,
   created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
INSERT INTO t_users (first_name,last_name,email,mobile) values ('Dat', 'Tran', 'hoangdat@gmail.com', '0393974369');
