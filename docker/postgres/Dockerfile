FROM postgres:12.2
COPY 000_init_tables.sql /docker-entrypoint-initdb.d/000_init_tables.sql
COPY 001_init_db_customer.sql /docker-entrypoint-initdb.d/001_init_db_customer.sql
COPY 002_init_db_order.sql /docker-entrypoint-initdb.d/002_init_db_order.sql
COPY 003_init_db_product.sql /docker-entrypoint-initdb.d/003_init_db_product.sql
