use mysql;

CREATE DATABASE naerodb;
SHOW DATABASES;

CREATE USER 'naero'@'%' IDENTIFIED BY  'naero';
SELECT * FROM mysql.user;

GRANT ALL PRIVILEGES ON naerodb.* TO 'naero'@'%';
SHOW GRANTS FOR 'naero'@'%';

use naerodb;