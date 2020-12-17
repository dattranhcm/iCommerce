#!/bin/bash
set -e
echo 'preparing to create dbuser table'
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE dbuser;
    GRANT ALL PRIVILEGES ON DATABASE dbuser TO dbuser;
EOSQL
echo 'preparing to create dborder table'
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE dborder;
    GRANT ALL PRIVILEGES ON DATABASE dborder TO dbuser;
EOSQL
