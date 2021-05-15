CREATE DATABASE `Hotel`;
USE `Hotel`;

CREATE TABLE `employees`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`first_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`title` VARCHAR(30) NOT NULL,
`notes` TEXT
);

CREATE TABLE `customers`(
`account_number` INT NOT NULL UNIQUE PRIMARY KEY,
`first_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`phone_number` VARCHAR(30) NOT NULL,
`emergency_name` VARCHAR(30),
`emergency_number` VARCHAR(30),
`notes` TEXT
);

CREATE TABLE `room_status`(
`room_status` VARCHAR(10) NOT NULL UNIQUE PRIMARY KEY,
`notes` TEXT
);

CREATE TABLE `room_types`(
`room_type` VARCHAR(10) NOT NULL UNIQUE PRIMARY KEY,
`notes` TEXT
);

CREATE TABLE `bed_types`(
`bed_type` VARCHAR(16) NOT NULL UNIQUE PRIMARY KEY,
`notes` TEXT
);

CREATE TABLE `rooms`(
`room_number` INT NOT NULL UNIQUE PRIMARY KEY,
`room_type` VARCHAR(10) NOT NULL,
`bed_type` VARCHAR(16) NOT NULL,
`rate` INT NOT NULL,
`room_status` VARCHAR(10) NOT NULL,
`notes` TEXT,

CONSTRAINT `fk_rooms_room_types` FOREIGN KEY (`room_type`) REFERENCES `room_types`(`room_type`),
CONSTRAINT `fk_rooms_bed_types` FOREIGN KEY (`bed_type`) REFERENCES `bed_types`(`bed_type`),
CONSTRAINT `fk_rooms_room_status` FOREIGN KEY (`room_status`) REFERENCES `room_status`(`room_status`)
);

CREATE TABLE `payments`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`employee_id` INT NOT NULL,
`payment_date` DATE,
`account_number` INT NOT NULL,
`first_date_occupied` DATE,
`last_date_occupied` DATE,
`total_days` INT NOT NULL,
`amount_charged` DOUBLE NOT NULL,
`tax_rate` DOUBLE,
`tax_amount` DOUBLE NOT NULL,
`payment_total` DOUBLE NOT NULL,
`notes` TEXT,

CONSTRAINT `fk_payments_employees` FOREIGN KEY (`employee_id`) REFERENCES `employees`(`id`),
CONSTRAINT `fk_payments_customers` FOREIGN KEY (`account_number`) REFERENCES `customers`(`account_number`)
);

CREATE TABLE `occupancies`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`employee_id` INT NOT NULL,
`date_occupied` DATE,
`account_number` INT NOT NULL,
`room_number` INT NOT NULL,
`rate_applied` DOUBLE,
`phone_charge` DOUBLE,
`notes` TEXT,

CONSTRAINT `fk_occupancies_employees` FOREIGN KEY (`employee_id`) REFERENCES `employees`(`id`),
CONSTRAINT `fk_occupancies_rooms` FOREIGN KEY (`room_number`) REFERENCES `rooms`(`room_number`),
CONSTRAINT `fk_occupancies_customers` FOREIGN KEY (`account_number`) REFERENCES `customers`(`account_number`)
);

INSERT INTO `employees`
(`id`,`first_name`,`last_name`,`title`,`notes`)
VALUES
(1,'Svilen','Velikov','Mr.','bla'),
(2,'Mira','Dimitrova','Mrs.','bla bla'),
(3,'Ivan','Dragan','Mr.','bla');


INSERT INTO `customers`
(`account_number`,`first_name`,`last_name`,`phone_number`,`emergency_name`,`emergency_number`,`notes`)
VALUES
(1234,'Ivan','Ivanovich','+359898822977',NULL,NULL,'bez emergency name'),
(4567,'Shisham','Tsarev','+359898822978','Tarnovski','*88','bez emergency name'),
(1256,'Petar','Petrov','+359898822977',NULL,NULL,'bez emergency name');


INSERT INTO `room_status`
(`room_status`,`notes`)
VALUES
('Busy','The room is busy'),
('Free','The room is free'),
('Cleaning','The room is being cleaned at the moment');

INSERT INTO `room_types`
(`room_type`,`notes`)
VALUES
('apartment',NULL),
('studio','big apartment'),
('single','A single room');

INSERT INTO `bed_types`
(`bed_type`,`notes`)
VALUES
('single-person',NULL),
('double-person', 'двойно легло'),
('large',NULL);

INSERT INTO `rooms`
(`room_number`,`room_type`,`bed_type`,`rate`,`room_status`,`notes`)
VALUES
(111,'apartment','double-person',9,'Free',NULL),
(211,'studio','large',10,'Cleaning',NULL),
(311,'single','single-person',8,'Busy',NULL);

INSERT INTO `payments`
(`id`,`employee_id`,`payment_date`,`account_number`,`first_date_occupied`,`last_date_occupied`,`total_days`,
`amount_charged`,`tax_rate`,`tax_amount`,`payment_total`,`notes`)
VALUES
(1,1,'2020-10-29',1234,NULL,NULL,10,1000.0,0.2,200.0,1200,'How to calculate it'),
(2,3,'2021-11-29',4567,'2021-11-24','2021-11-24',5,1000.0,0.2,200.0,1200,NULL),
(3,2,'2019-01-15',1256,NULL,NULL,10,1000.0,0.2,200.0,1200,NULL);

INSERT INTO `occupancies`
(`id`,`employee_id`,`date_occupied`,`account_number`,`room_number`,`rate_applied`,`phone_charge`,`notes`)
VALUES
(1,3,NULL,1234,111,9.2,NULL,NULL),
(2,1,'2020-03-21',1256,211,10.3,NULL,NULL),
(3,2,'2019-05-18',4567,311,9.7,NULL,NULL);

UPDATE `payments` SET `tax_rate` = `tax_rate` * 0.97 WHERE (`id` > 0);
SELECT `tax_rate` FROM `payments`;

TRUNCATE TABLE `occupancies`;
