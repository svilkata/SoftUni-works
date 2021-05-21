CREATE DATABASE `Task 1`;
USE `Task 1`;

CREATE TABLE mountains(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL
);

CREATE TABLE peaks(
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `mountain_id` INT,
  CONSTRAINT `fk_peaks_mountains` 
  FOREIGN KEY (`mountain_id`)
  REFERENCES `mountains`(`id`)
  );
  
  #Task 2
  SELECT v.`driver_id`, v.`vehicle_type`,
  CONCAT(c.`first_name`, ' ', c.`last_name`) AS 'driver_name'
  FROM `vehicles` AS v
  JOIN `campers` AS c 
  ON v.`driver_id` = c.`id`;
  
  #Task 3
  SELECT r.`starting_point`, r.`end_point`, c.`id`, CONCAT (c.`first_name`, ' ', c.`last_name`) AS 'leader_name'
  FROM `routes` AS r
  JOIN `campers` AS c
  ON c.`id` = r.`leader_id`;
  
    
  #Task 4
  CREATE TABLE `mountains`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL
);

CREATE TABLE `peaks`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL,
`mountain_id` INT,
CONSTRAINT `fk_mountain_id` 
FOREIGN KEY(`mountain_id`)
REFERENCES `mountains`(`id`)
ON DELETE CASCADE
);


#Task 5
CREATE SCHEMA project_management;
USE project_management;
CREATE TABLE clients(
id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
client_name VARCHAR(100)
);

CREATE TABLE employees(
id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
first_name VARCHAR(30),
last_name VARCHAR(30),
project_id INT(11)
);

CREATE TABLE projects(
id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
client_id INT(11),
project_lead_id INT(11),
CONSTRAINT fk_project_asd
FOREIGN KEY (client_id)
REFERENCES clients(id)
);

ALTER TABLE employees
ADD CONSTRAINT fk_emp_lead
FOREIGN KEY (project_id)
REFERENCES projects(project_lead_id);


  

  
