#CREATE SCHEMA stc;

#1 Task
CREATE TABLE `addresses`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL
);

CREATE TABLE `categories`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL
);

CREATE TABLE `clients`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`full_name` VARCHAR(50) NOT NULL,
`phone_number` VARCHAR(20) NOT NULL
);

CREATE TABLE `drivers`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`age` INT NOT NULL,
`rating` FLOAT DEFAULT 5.5
);

CREATE TABLE `cars`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`make` VARCHAR(20) NOT NULL,
`model` VARCHAR(20),
`year` INT NOT NULL DEFAULT 0,
`mileage` INT DEFAULT 0,
`condition` CHAR(1) NOT NULL,
`category_id` INT NOT NULL,
CONSTRAINT fk_cars_categories
FOREIGN KEY `cars`(`category_id`)
REFERENCES `categories`(`id`)
);

CREATE TABLE `courses`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`from_address_id` INT NOT NULL,
`start` DATETIME NOT NULL,
`bill` DECIMAL(10, 2) DEFAULT 10,
`car_id` INT NOT NULL,
`client_id` INT NOT NULL,

CONSTRAINT fk_courses_addresses
FOREIGN KEY `courses`(`from_address_id`)
REFERENCES `addresses`(`id`),

CONSTRAINT fk_courses_cars
FOREIGN KEY `courses`(`car_id`)
REFERENCES `cars`(`id`),

CONSTRAINT fk_courses_clients
FOREIGN KEY `courses`(`client_id`)
REFERENCES `clients`(`id`)
);

CREATE TABLE `cars_drivers`(
`car_id` INT NOT NULL,
`driver_id` INT NOT NULL,

CONSTRAINT pk_carid_driverid
PRIMARY KEY `cars_drivers`(`car_id`, `driver_id`),

CONSTRAINT fk_cd_cars
FOREIGN KEY `cars_drivers`(`car_id`)
REFERENCES `cars`(`id`),

CONSTRAINT fk_cd_drivers
FOREIGN KEY `cars_drivers`(`driver_id`)
REFERENCES `drivers`(`id`)
);

#2.	Insert
INSERT INTO `clients`(`full_name`, `phone_number`)
SELECT CONCAT(`first_name`, ' ', `last_name`) AS `full_name`,
CONCAT('(088) 9999', `id` * 2) AS `phone_number`
FROM `drivers`
WHERE `id` BETWEEN 10 AND 20;


#3. Update
UPDATE `cars`
SET `condition` = 'C'
WHERE (`mileage` >= 800000 OR `mileage` IS NULL) AND `year` <= 2010 AND `make` <> 'Mercedes-Benz';
SELECT COUNT(*) FROM cars WHERE `condition` = 'C';

#4. Delete
DELETE cl FROM `clients` AS cl 
LEFT JOIN `courses` AS co
ON cl.`id` = co.`client_id`
WHERE co.`id` IS NULL AND CHAR_LENGTH(cl.`full_name`) > 3;

#5. Cars
SELECT `make`, `model`, `condition`
FROM `cars`
ORDER BY `id`;

#6. Drivers and Cars
SELECT dr.`first_name`, dr.`last_name`, c.`make`, c.`model`, c.`mileage` 
FROM `drivers` AS dr
JOIN `cars_drivers` AS cd
ON dr.`id` = cd.`driver_id`
JOIN `cars` AS c
ON c.`id` = cd.`car_id`
WHERE c.`mileage` IS NOT NULL
ORDER BY c.`mileage` DESC, dr.`first_name` ASC;


#7.	Number of courses for each car
SELECT cr.`id` AS `car_id`, 
cr.`make`,
cr.`mileage`,
COUNT(cs.`id`) AS `count_of_courses`,
ROUND(AVG(cs.`bill`), 2) AS `avg_bill`
FROM `cars` as cr
LEFT JOIN `courses` AS cs
ON cr.`id` = cs.`car_id`
GROUP BY cr.`id`
HAVING `count_of_courses` <> 2
ORDER BY `count_of_courses` DESC, `car_id` ASC;

#8. Regular clients
SELECT cl.`full_name`, COUNT(cr.`id`) AS `count_of_cars`,
SUM(cs.`bill`) AS `total_sum`
FROM `clients` AS cl
LEFT JOIN `courses` AS cs
ON cl.`id` = cs.`client_id`
LEFT JOIN `cars` AS cr
ON cr.`id` = cs.`car_id`
WHERE cl.`full_name` LIKE ('_a%')
GROUP BY cl.`id`
HAVING `count_of_cars` > 1
ORDER BY cl.`full_name` ASC;

#9.	Full information of courses
SELECT adr.`name`,
(
CASE 
	WHEN DATE_FORMAT(cs.`start`, '%H') BETWEEN 6 AND 20 THEN 'Day'
    WHEN DATE_FORMAT(cs.`start`, '%H') IN (21, 22, 23, 24, 0, 1, 2, 3, 4, 5) THEN 'Night'
	ELSE NULL 
END ) AS `day_time`,
cs.`bill`, cl.`full_name`, cr.`make`, cr.`model`, ctrgs.`name`
FROM `addresses` AS adr
LEFT JOIN `courses` AS cs
ON adr.`id` = cs.`from_address_id`
LEFT JOIN `cars` AS cr
ON cr.`id` = cs.`car_id`
LEFT JOIN `clients` AS cl
ON cl.`id` = cs.`client_id`
LEFT JOIN `categories` AS ctrgs
ON ctrgs.`id` = cr.`category_id`
WHERE cs.`start` IS NOT NULL
ORDER BY cs.`id` ASC;


#10. Find all courses by client’s phone number
DELIMITER $$$$
CREATE FUNCTION udf_courses_by_client(`phone_num` VARCHAR (20))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN(SELECT COUNT(cs.`id`)
    FROM `clients` AS cl
    RIGHT JOIN `courses` AS cs
    ON cs.`client_id` = cl.`id`
    WHERE cl.`phone_number` = `phone_num`);
END; 
$$$$

SELECT udf_courses_by_client ('(803) 6386812') as `count`; 

#11. Full info for address
DELIMITER %%
CREATE PROCEDURE udp_courses_by_address(`address_name` VARCHAR(100)) 
BEGIN
  SELECT adr.`name`, cl.`full_name` AS `full_names`,
(CASE 
	WHEN cs.`bill` <= 20 THEN 'Low'
	WHEN cs.`bill` <= 30 THEN 'Medium'
	ELSE 'High'
END) AS `level_of_bill`,
cr.`make`, cr.`condition`, ctrgs.`name` AS `cat_name`
FROM `addresses` AS adr
LEFT JOIN `courses` AS cs
ON adr.`id` = cs.`from_address_id`
LEFT JOIN `cars` AS cr
ON cr.`id` = cs.`car_id`
LEFT JOIN `clients` AS cl
ON cl.`id` = cs.`client_id`
LEFT JOIN `categories` AS ctrgs
ON ctrgs.`id` = cr.`category_id`
WHERE cs.`start` IS NOT NULL AND adr.`name` = `address_name`
ORDER BY cr.`make` ASC, cl.`full_name`;
END %%

CALL udp_courses_by_address('66 Thompson Drive');

