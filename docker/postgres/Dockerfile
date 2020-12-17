FROM postgres:12.2
#COPY init-user-db.sh /docker-entrypoint-initdb.d/init-user-db.sh
COPY 000_init_tables.sql /docker-entrypoint-initdb.d/000_init_tables.sql
COPY 001_init_dbuser_table.sql /docker-entrypoint-initdb.d/001_init_dbuser_table.sql
COPY 002_init_dborder_table.sql /docker-entrypoint-initdb.d/002_init_dborder_table.sql
#FROM mysql:5.6
#COPY setup.sh /mysql/setup.sh
#COPY 001_init_dbuser_table.sql /mysql/001_init_dbuser_table.sql
#RUN chmod +x /mysql/setup.sh
