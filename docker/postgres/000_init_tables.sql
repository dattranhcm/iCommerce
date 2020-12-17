SELECT 'CREATE DATABASE dborder'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'dborder');
SELECT 'CREATE DATABASE dbuser'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'dbuser');