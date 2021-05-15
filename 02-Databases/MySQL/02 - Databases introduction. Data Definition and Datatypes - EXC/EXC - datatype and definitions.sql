/*CREATE DATABASE `minions`;
USE `minions`;

CREATE TABLE `minions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT,
  PRIMARY KEY (`id`));

CREATE TABLE `towns` (
  `town_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY(`town_id`));
  
ALTER TABLE `towns` 
CHANGE COLUMN `town_id` `id` INT NOT NULL AUTO_INCREMENT ;
  
ALTER TABLE `minions`
ADD COLUMN town_id INT NOT NULL; 
  
ALTER TABLE `minions` 
ADD CONSTRAINT `fk_minions_towns`
FOREIGN KEY (`town_id`)
REFERENCES `towns` (`id`);

INSERT INTO `towns` (`id`, `name`)
VALUES (1, 'Sofia'),
(2, 'Plovdiv'),
(3, 'Varna');

INSERT INTO `minions`(`id`, `name`, `age`, `town_id`)
VALUES (1, 'Kevin', 22, 1),
(2, 'Bob', 15, 3),
(3, 'Steward', NULL, 2);

TRUNCATE TABLE `minions`;

DROP TABLE `minions`;
DROP TABLE `towns`;*/

/*CREATE DATABASE `soft_uni`;
USE `soft_uni`;

CREATE TABLE `towns`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(30) NOT NULL
);

CREATE TABLE `addresses`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`address_text` VARCHAR(255) NOT NULL,
`town_id` INT,
CONSTRAINT `fk_addresss_towns`
FOREIGN KEY `addresses`(`town_id`)
REFERENCES `towns`(`id`));

CREATE TABLE `departments`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50) NOT NULL
);

INSERT INTO `towns`
(`id`,
`name`)
VALUES
(1, 'Sofia'),
(2, 'Plovdiv'),
(3, 'Varna'),
(4, 'Burgas');

INSERT INTO `departments`
(`id`,
`name`)
VALUES
(1,'Engineering'),
(2,'Sales'),
(3,'Marketing'),
(4,'Software Development'),
(5,'Quality Assurance');

CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `middle_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `job_title` varchar(45) NOT NULL,
  `department_id` int DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  
  CONSTRAINT `fk_employees_department` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`),
  CONSTRAINT `fk_employees_addresses` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`)  
);

INSERT INTO `employees`
(`id`,
`first_name`,
`middle_name`,
`last_name`,
`job_title`,
`department_id`,
`hire_date`,
`salary`,
`address_id`
)
VALUES
(1, 'Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013/02/01', 3500.00, NULL),
(2, 'Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004/03/02', 4000.00, NULL),
(3, 'Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016/08/28', 525.25, NULL),
(4, 'Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007/12/09', 3000.00, NULL),
(5, 'Peter', 'Pan', 'Pan', 'Intern', 3, '2016/08/28', 599.88, NULL);


SELECT * FROM `towns`;
SELECT * FROM `departments`;
SELECT * FROM `employees`;

SELECT * FROM `towns`
ORDER BY `name`;

SELECT * FROM `departments`
ORDER BY `name`;

SELECT * from `employees`
ORDER BY `salary` DESC;


SELECT `name` FROM `towns`
ORDER BY `name`;

SELECT `name` FROM `departments`
ORDER BY `name`;

SELECT `first_name`, `last_name`, `job_title`, `salary` from `employees`
ORDER BY `salary` DESC;

TRUNCATE TABLE `employees`;

UPDATE `employees`
SET `salary` = `salary` * 1.1
WHERE `id` > 0;

SELECT `salary` FROM `employees`;*/

CREATE DATABASE `test`;
USE `test`;

CREATE TABLE `people`(
`id` INT NOT NULL UNIQUE AUTO_INCREMENT,
`name` NCHAR(200) NOT NULL,
`picture` BLOB,
`height` DOUBLE(3,2),
`weight` DOUBLE(5,2),
`gender` CHAR(1) NOT NULL,
`birthdate` DATE NOT NULL,
`biography` TEXT,
PRIMARY KEY(`id`)
);

INSERT INTO `people`
(`id`,
`name`,
`picture`,
`height`,
`weight`,
`gender`,
`birthdate`,
`biography`)
VALUES
(1,'Pesho', NULL, 1.45, 10.01, 'm', '1985-04-03', 'Razkazvam za sebe si'),
(2,'Ivo', NULL, 1.75, 70.35, 'f', '1992-04-03', 'I taka'),
(3,'Stoyan', NULL, 2.01, 110.01, 'm', '1985-04-03', 'Vsichko mi e nared'),
(4,'Plamen', NULL, 1.78, 88.01, 'm', '1986-04-03', 'Tsykam si baza danni'),
(5,'Marina', NULL, 1.92, 99.01, 'f', '2020-08-28', 'I mi e dobre');

USE `test`;
CREATE TABLE `users`(
`id` INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
`username` CHAR(30) UNIQUE NOT NULL,
`password` CHAR(26) NOT NULL,
`profile_picture` BLOB,
`last_login_time` DATETIME,
`is_deleted` BOOLEAN NOT NULL
);

INSERT INTO `users` (`id`, `username`, `password`, `profile_picture`, `last_login_time`, `is_deleted`)
VALUES 
(1, 'Ivan', 'mypass123', NULL, CURRENT_TIMESTAMP, TRUE),
(2, 'Petran', 'mypass123', NULL, '2015-03-27 15:15:00', FALSE),
(3, 'Svilen', 'mypass123', NULL, DEFAULT, TRUE),
(4, 'MIroslava', 'mypass123', NULL, '2015-03-27 15:15:00', FALSE),
(5, 'Nadezhda', 'mypass123', NULL, '2015-03-27 15:15:00', TRUE);


ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_users`
PRIMARY KEY(`id`, `username`);

ALTER TABLE `users` 
CHANGE COLUMN `last_login_time` `last_login_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ;

INSERT INTO `users` (`id`, `username`, `password`, `profile_picture`, `last_login_time`, `is_deleted`)
VALUES (6, 'Bogdan', 'mypass123', NULL, DEFAULT, TRUE);

ALTER TABLE `users` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`);

ALTER TABLE `users` 
ADD CONSTRAINT `pk_users`
UNIQUE(`username`);


