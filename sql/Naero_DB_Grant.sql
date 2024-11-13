use mysql;

CREATE DATABASE naerodb;
SHOW DATABASES;

CREATE USER 'naero'@'%' IDENTIFIED BY  'naero';
SELECT * FROM mysql.user;

GRANT ALL PRIVILEGES ON naerodb.* TO 'naero'@'%';
GRANT SUPER ON *.* TO 'naero'@'%';
SHOW GRANTS FOR 'naero'@'%';
SET GLOBAL log_bin_trust_function_creators = 1;

use naerodb;
