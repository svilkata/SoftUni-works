CREATE DATABASE demo_tr;
USE demo_tr;

#Task 1
CREATE TABLE `passports` (
`passport_id` INT PRIMARY KEY NOT NULL UNIQUE,
`passport_number` VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO `passports` (`passport_id`, `passport_number`)
VALUES 
(101, 'N34FG21B'),
(102, 'K65LO4R7'),
(103, 'ZE657QP2');

CREATE TABLE `people` (
`person_id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
`first_name` VARCHAR(40) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL,
`passport_id` INT NOT NULL UNIQUE
);

/*ALTER TABLE `persons`
CHANGE COLUMN `person_id` `person_id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY;*/

ALTER TABLE `people`
ADD CONSTRAINT `fk_persons_passports`
FOREIGN KEY `people`(`passport_id`)
REFERENCES `passports`(`passport_id`);

INSERT INTO `people` (`person_id`, `first_name`, `salary`, `passport_id`)
VALUES 
(1, 'Roberto', '43300.00', 102),
(2, 'Tom', '56100.00', 103),
(3, 'Yana', '60200.00', 101);

#Task 2
CREATE TABLE `manufacturers` (
`manufacturer_id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
`name` VARCHAR(30) NOT NULL,
`established_on` DATE NOT NULL
);

INSERT INTO `manufacturers` (`name`, `established_on`)
VALUES 
('BMW', '1916-03-01'),
('Tesla', '2003-01-01'),
('Lada', '1966-05-01');

CREATE TABLE `models` (
`model_id` INT PRIMARY KEY,
`name` VARCHAR(20),
`manufacturer_id` INT,

CONSTRAINT fk_models_manufactures
FOREIGN KEY `models`(`manufacturer_id`)
REFERENCES `manufacturers`(`manufacturer_id`)
);

INSERT INTO `models`
VALUES
(101, 'X1', 1),
(102, 'i6', 1),
(103, 'Model S', 2),
(104, 'Model X', 2),
(105, 'Model 3', 2),
(106, 'Nova', 3);

#Task 3 - create mapping table - composite primary key
CREATE TABLE `students` (
`student_id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
`name` VARCHAR(30)
);

CREATE TABLE `exams` (
`exam_id` INT NOT NULL PRIMARY KEY,
`name` VARCHAR(30)
);

INSERT INTO `students`
VALUES 
(1, 'Mila'),
(2, 'Toni'),
(3, 'Ron');

INSERT INTO `exams`
VALUES 
(101, 'Spring MVC'),
(102, 'Neo4j'),
(103, 'Oracle 11g');

CREATE TABLE `students_exams`(
`student_id` INT,
`exam_id` INT,

CONSTRAINT pk_students_exams
PRIMARY KEY `students_exams`(`student_id`, `exam_id`),

CONSTRAINT fk_students_exams_students
FOREIGN KEY `students_exams`(`student_id`)
REFERENCES `students`(`student_id`),

CONSTRAINT fk_students_exams_exams
FOREIGN KEY `students_exams`(`exam_id`)
REFERENCES `exams`(`exam_id`)
);

INSERT INTO `students_exams`
VALUES 
(1, 101),
(1, 102),
(2, 101),
(3, 103),
(2, 102),
(2, 103);

#Task 4 - Self-referencing
CREATE TABLE `teachers`(
`teacher_id` INT PRIMARY KEY,
`name` VARCHAR(20),
`manager_id` INT,

CONSTRAINT fk_teachers_teachers
FOREIGN KEY (`manager_id`)
REFERENCES `teachers`(`teacher_id`)
);

INSERT INTO `teachers` (`teacher_id`, `name`)
VALUES
(101, 'John'),
(102, 'Maya'),
(103, 'Silvia'),
(104, 'Ted'),
(105, 'Mark'),
(106, 'Greta');

UPDATE `teachers` SET `manager_id` = null WHERE (`name` = 'John');
UPDATE `teachers` SET `manager_id` = 106 WHERE (`name` = 'Maya');
UPDATE `teachers` SET `manager_id` = 106 WHERE (`name` = 'Silvia');
UPDATE `teachers` SET `manager_id` = 105 WHERE (`name` = 'Ted');
UPDATE `teachers` SET `manager_id` = 101 WHERE (`name` = 'Mark');
UPDATE `teachers` SET `manager_id` = 101 WHERE (`name` = 'Greta');


#Task 5
CREATE SCHEMA `Online Store Database`;
USE `Online Store Database`;

CREATE TABLE `cities`(
`city_id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50)
);

CREATE TABLE `customers` (
`customer_id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50),
`birthday` DATE,
`city_id` INT,

CONSTRAINT fk_customer_cities
FOREIGN KEY `customers`(`city_id`)
REFERENCES `cities`(`city_id`)
);

CREATE TABLE `orders` (
`order_id` INT AUTO_INCREMENT PRIMARY KEY,
`customer_id` INT,

CONSTRAINT fk_orders_customers
FOREIGN KEY `orders`(`customer_id`)
REFERENCES `customers`(`customer_id`)
);

CREATE TABLE `item_types` (
`item_type_id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50)
);

CREATE TABLE `items` (
item_id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50),
`item_type_id` INT,

CONSTRAINT fk_items_item_types
FOREIGN KEY (`item_type_id`)
REFERENCES `item_types`(`item_type_id`)
);

CREATE TABLE `order_items` (
`order_id` INT,
`item_id` INT,

CONSTRAINT pk_orders_items
PRIMARY KEY (`order_id`, `item_id`),

CONSTRAINT fk_order_items_orders
FOREIGN KEY `order_items`(`order_id`)
REFERENCES 	`orders`(`order_id`),

CONSTRAINT fk_order_items_items
FOREIGN KEY `order_items`(`item_id`)
REFERENCES 	`items`(`item_id`)
);

#Task 06
CREATE SCHEMA `University`;
USE `University`;

CREATE TABLE `majors` (
`major_id` INT PRIMARY KEY,
`name` VARCHAR(50)
);

CREATE TABLE `students`(
`student_id` INT PRIMARY KEY,
`student_number` VARCHAR(12),
`student_name` VARCHAR(50),
`major_id` INT,

CONSTRAINT fk_students_majors
FOREIGN KEY `students`(`major_id`)
REFERENCES `majors`(`major_id`)
);

CREATE TABLE `payments`(
`payment_id` INT PRIMARY KEY,
`payment_date` DATE,
`payment_amount` DECIMAL(8,2),
`student_id` INT,

CONSTRAINT fk_payments_students
FOREIGN KEY `payments`(`student_id`)
REFERENCES `students`(`student_id`)
);

CREATE TABLE `subjects`(
`subject_id` INT PRIMARY KEY,
`subject_name` VARCHAR(50)
);

CREATE TABLE `agenda` (
`student_id` INT,
`subject_id` INT,

CONSTRAINT pk_students_subjects
PRIMARY KEY `agenda`(`student_id`, `subject_id`),

CONSTRAINT fk_agenda_students
FOREIGN KEY `agenda`(`student_id`)
REFERENCES `students`(`student_id`),

CONSTRAINT fk_agenda_subjects
FOREIGN KEY `agenda`(`subject_id`)
REFERENCES `subjects`(`subject_id`)
);

#Task 09
SELECT m.`mountain_range`, p.`peak_name`, p.`elevation` AS 'peak_elevation'
FROM `mountains` AS m
JOIN `peaks` AS p
WHERE m.`mountain_range` = 'Rila' && p.`mountain_id` = 17
ORDER BY `peak_elevation` DESC;


