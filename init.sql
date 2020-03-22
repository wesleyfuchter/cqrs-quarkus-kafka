CREATE DATABASE bankaccount;
CREATE USER bankaccount WITH ENCRYPTED PASSWORD 'bankaccount';
GRANT ALL PRIVILEGES ON DATABASE bankaccount TO bankaccount;