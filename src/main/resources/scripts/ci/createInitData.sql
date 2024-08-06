CREATE DATABASE calender;

CREATE USER 'choi'@'localhost' IDENTIFIED BY 'm98dev09tmd@*';
GRANT ALL PRIVILEGES ON calender.* TO 'choi'@'localhost';
FLUSH PRIVILEGES;