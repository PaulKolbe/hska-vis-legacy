CREATE DATABASE IF NOT EXISTS categorydb;
CREATE DATABASE IF NOT EXISTS productdb;
CREATE DATABASE IF NOT EXISTS webshop;

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'webshopuser'@'%' WITH GRANT OPTION;


USE categorydb;

CREATE TABLE IF NOT EXISTS category (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;



USE productdb;

CREATE TABLE IF NOT EXISTS product (
	id INT NOT NULL AUTO_INCREMENT,
	details VARCHAR(255),
	name VARCHAR(255),
	price DOUBLE,
	category_id INT,
	category_name VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;



USE webshop;

CREATE TABLE IF NOT EXISTS customer (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL,
	role INT NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS role (
	id INT NOT NULL AUTO_INCREMENT,
	level1 INT,
	type VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

insert into `role` (`level1`, `type`)
values (0, 'admin');
insert into `role` (`level1`, `type`)
values (1, 'user');

insert into `customer` (`name`, `lastname`, `password`, `username`, `role`)
values ('admin', 'admin', 'admin', 'admin', 1);
