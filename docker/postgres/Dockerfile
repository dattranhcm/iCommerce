#FROM postgres:12.2
#COPY init-user-db.sh /docker-entrypoint-initdb.d/init-user-db.sh
#COPY create_user_table.sql /docker-entrypoint-initdb.d/create_user_table.sql
FROM mysql:5.6
COPY setup.sh /mysql/setup.sh
COPY create_user_table.sql /mysql/create_user_table.sql
RUN chmod +x /mysql/setup.sh
