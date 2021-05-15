CREATE DATABASE `car_rental`;
USE `car_rental`;

CREATE TABLE `categories`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`category` VARCHAR(45) NOT NULL,
`daily_rate` DOUBLE NOT NULL,
`weekly_rate` DOUBLE NOT NULL,
`monthly_rate` DOUBLE NOT NULL,
`weekend_rate` DOUBLE NOT NULL
);

CREATE TABLE `cars`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`plate_number` VARCHAR(10),
`mark` VARCHAR(10) NOT NULL,
`model` VARCHAR(10) NOT NULL,
`car_year` INT NOT NULL,
`category_id` INT NOT NULL,
`doors` INT NOT NULL,
`picture` BLOB,
`car_condition` VARCHAR(40) NOT NULL,
`available` BOOLEAN NOT NULL,

CONSTRAINT `fk_cars_categories`
    FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`)
);

CREATE TABLE `employees`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`first_name` VARCHAR(45) NOT NULL,
`last_name` VARCHAR(45) NOT NULL,
`title` VARCHAR(45),
`notes` TEXT
);

CREATE TABLE `customers`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`driver_licence_number` VARCHAR(45) NOT NULL,
`full_name` VARCHAR(45) NOT NULL,
`address` VARCHAR(45),
`city` VARCHAR(45) NOT NULL,
`zip_code` INT,
`notes` TEXT
);

CREATE TABLE `rental_orders`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`employee_id` INT NOT NULL,
`customer_id` INT NOT NULL,
`car_id` INT NOT NULL,
`car_condition` VARCHAR(40) NOT NULL,
`tank_level` INT,
`kilometrage_start` INT NOT NULL,
`kilometrage_end` INT NOT NULL,
`total_kilometrage` INT NOT NULL,
`start_date` DATE NOT NULL,
`end_date` DATE NOT NULL,
`total_days` INT NOT NULL,
`rate_applied` DOUBLE,
`tax_rate` DOUBLE NOT NULL,
`order_status` VARCHAR(15),
`notes` TEXT,

CONSTRAINT `fk_rental_orders_employees` FOREIGN KEY (`employee_id`) REFERENCES `employees`(`id`),
CONSTRAINT `fk_rental_orders_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers`(`id`),
CONSTRAINT `fk_rental_orders_cars` FOREIGN KEY (`car_id`) REFERENCES `cars`(`id`)
);


INSERT INTO `categories`
(`id`,`category`,`daily_rate`,`weekly_rate`,`monthly_rate`,`weekend_rate`)
VALUES
(1,'Sport',1.1,1.2,1.3,1.4),
(2,'Normal',1.1,1.2,1.3,1.4),
(3,'OldFashioned',1.1,1.2,1.3,1.4);

INSERT INTO `car_rental`.`cars`
(`id`, `plate_number`, `mark`, `model`, `car_year`, `category_id`, `doors`, `picture`, `car_condition`, `available`)
VALUES
(1, 'H3109BM','Opel','Astra',2000,2,5,NULL,'BAD',TRUE),
(2, 'ZZZ 888','Lamubrgini','Diablo',2018,1,2,NULL,'EXCELLENT',TRUE),
(3, NULL,'Reanult','19',1990,3,5,NULL,'BAD',FALSE);

INSERT INTO `employees`
(`id`,`first_name`,`last_name`,`title`,`notes`)
VALUES
(1,'Svilen','Velikov','Mr.','Goten pich'),
(2,'Ivan','Ivanov',NULL,NULL),
(3,'Miroslava','Diomitrova','Mrs.','Super matse');

INSERT INTO `customers`
(`id`,`driver_licence_number`,`full_name`,`address`,`city`,`zip_code`,`notes`)
VALUES
(1,'Licence12345','Ivan Dragievich',NULL,'Sofia',1000,'mogat da dadat i pobeche pari za kolata'),
(2,'Licence56789','Petko Petkov',NULL,'Shumen',7000,NULL),
(3,'Licence13579','Kirkor Nazarqn','ul. Manastirska livada 5','Sofia',NULL,'byrzat');

INSERT INTO `car_rental`.`rental_orders`
(`id`,`employee_id`,`customer_id`,`car_id`,`car_condition`,`tank_level`,`kilometrage_start`,`kilometrage_end`,`total_kilometrage`,
`start_date`,`end_date`,`total_days`,`rate_applied`,`tax_rate`,`order_status`,`notes`)
VALUES
(1,1,2,3,'good',NULL,155000,196000,50000,'2020-08-30','2020-10-15',45,1.1,0.55,TRUE,'bla bla bla'),
(2,3,3,2,'Exclellent',NULL,155000,196000,50000,'2020-08-30','2020-09-30',30,1.1,0.55,TRUE,'bla bla bla'),
(3,1,2,3,'good',NULL,155000,196000,50000,'2020-08-30','2020-09-15',15,1.1,0.55,TRUE,'bla bla bla');


