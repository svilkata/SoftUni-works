#1.	Table Design
CREATE TABLE `countries`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL
);

CREATE TABLE `towns`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `country_id` INT NOT NULL,    
    CONSTRAINT fk_towns_countries
    FOREIGN KEY `towns`(`country_id`)
    REFERENCES `countries`(`id`)
);

CREATE TABLE `stadiums`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `capacity` INT NOT NULL,
    `town_id` INT NOT NULL,    
    CONSTRAINT fk_stadiums_towns
    FOREIGN KEY `stadiums`(`town_id`)
    REFERENCES `towns`(`id`)
);

CREATE TABLE `teams`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `established` DATE NOT NULL,
    `fan_base` BIGINT(20) NOT NULL DEFAULT 0,
    `stadium_id` INT NOT NULL,
    CONSTRAINT fk_teams_stadiums
    FOREIGN KEY `teams`(`stadium_id`)
    REFERENCES `stadiums`(`id`)
);

CREATE TABLE `skills_data`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `dribbling` INT DEFAULT 0,
    `pace` INT DEFAULT 0,
    `passing` INT DEFAULT 0,
    `shooting` INT DEFAULT 0,
    `speed` INT DEFAULT 0,
    `strength` INT DEFAULT 0
);

CREATE TABLE `coaches`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(10) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `salary` DECIMAL(10, 2) NOT NULL DEFAULT 0,
    `coach_level` INT NOT NULL DEFAULT 0
);

CREATE TABLE `players`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(10) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `age` INT NOT NULL DEFAULT 0,
    `position` CHAR(1) NOT NULL,
    `salary` DECIMAL(10, 2) NOT NULL DEFAULT 0,
    `hire_date` DATETIME,
    `skills_data_id` INT NOT NULL,
    `team_id` INT,
    CONSTRAINT fk_players_teams
	FOREIGN KEY `players`(`team_id`)
    REFERENCES `teams`(`id`),
    CONSTRAINT fk_players_skills
	FOREIGN KEY `players`(`skills_data_id`)
    REFERENCES `skills_data`(`id`)    
);

CREATE TABLE `players_coaches`(
	`player_id` INT,
    `coach_id` INT,
	CONSTRAINT pk_players_coaches
	PRIMARY KEY `players_coaches`(`player_id`, `coach_id`),

	CONSTRAINT fk_pc_player
	FOREIGN KEY `players_coaches`(`player_id`)
	REFERENCES `players`(`id`),

	CONSTRAINT fk_pc_coaches
	FOREIGN KEY `players_coaches`(`coach_id`)
	REFERENCES `coaches`(`id`)
);

#2.	Insert
INSERT INTO `coaches`(`first_name`, `last_name`, `salary`, `coach_level`)

SELECT `first_name`, `last_name`, `salary` + `salary`, 
CHAR_LENGTH(`first_name`)
FROM `players`
WHERE `age` >= 45;

#3.	Update
UPDATE `coaches`
SET `coach_level` = `coach_level` + 1
WHERE `first_name` LIKE 'A%' AND  
(SELECT COUNT(*) FROM `players_coaches` AS pc WHERE pc.`coach_id` = `id`) >= 1;


#4.	Delete
DELETE FROM `players`
WHERE `age` >= 45;

#5.	Players 
SELECT `first_name`, `age`, `salary`
FROM `players`
ORDER BY `salary` DESC;


#6. Young offense players without contract
SELECT p.`id`, CONCAT(p.`first_name`, ' ', p.`last_name`) AS `full_name`, p.`age`, p.`position`, p.`hire_date`

FROM `players` AS p
JOIN `skills_data` AS sd
ON p.`skills_data_id` = sd.`id`
WHERE sd.`strength` > 50 AND p.`position` = 'A' AND p.`age` < 23 AND p.`hire_date` IS NULL
ORDER BY p.`salary` ASC, p.`age` ASC;

#7. Detail info for all teams
SET sql_mode = 'ONLY_FULL_GROUP_BY';    
SET sql_mode = '';    
SELECT ANY_VALUE(t.`name`) AS `team_name`, ANY_VALUE(t.`established`) AS `established`, ANY_VALUE(t.`fan_base`) AS `fan_base`,
ANY_VALUE(COUNT(p.`id`)) AS `players_count`
FROM `teams` AS t
LEFT JOIN `players` AS p
ON t.`id` = p.`team_id`
GROUP BY t.`name`
ORDER BY `players_count` DESC, t.`fan_base` DESC;

SET sql_mode = 'ONLY_FULL_GROUP_BY';   
SELECT t.`name` AS `team_name`, t.`established`, t.`fan_base`, COUNT(p.`id`) AS `players_count`
FROM `teams` AS t
LEFT JOIN `players` AS p
ON t.`id` = p.`team_id`
GROUP BY t.`id`
ORDER BY `players_count` DESC, t.`fan_base` DESC;


#8. The fastest player by towns
SET sql_mode = 'ONLY_FULL_GROUP_BY';  
SELECT MAX(sd.`speed`) AS `max_speed`, t.`name` AS `town_name` 
FROM `skills_data` AS sd
RIGHT JOIN `players` AS p
ON p.`skills_data_id` = sd.`id`
RIGHT JOIN `teams` AS tm
ON p.`team_id` = tm.`id`
RIGHT JOIN `stadiums` AS s
ON tm.`stadium_id` = s.`id`
RIGHT JOIN `towns` AS t
ON s.`town_id` = t.`id`
WHERE tm.`name` <> 'Devify'
GROUP BY t.`id`
ORDER BY `max_speed` DESC, `town_name` ASC;


#9. Total salaries and players by cSELECT MAX(sd.`speed`) AS `max_speed`, t.`name` AS `town_name` 
SELECT cn.`name`,
COUNT(p.`id`) AS `total_count_of_players`,
SUM(p.`salary`) AS `total_sum_of_salaries`
FROM `players` AS p
RIGHT JOIN `teams` AS tm
ON p.`team_id` = tm.`id`
RIGHT JOIN `stadiums` AS s
ON tm.`stadium_id` = s.`id`
RIGHT JOIN `towns` AS t
ON s.`town_id` = t.`id`
RIGHT JOIN `countries` AS cn
ON t.`country_id` = cn.`id`
GROUP BY cn.`id`
ORDER BY `total_count_of_players` DESC, cn.`name` ASC;


#10. Find all players that play on stadium
DELIMITER $$$$
CREATE FUNCTION udf_stadium_players_count(stadium_name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN (SELECT COUNT(p.`id`) AS count
    FROM `players` AS p
    RIGHT JOIN `teams` AS tm
	ON p.`team_id` = tm.`id`
	RIGHT JOIN `stadiums` AS s
	ON tm.`stadium_id` = s.`id`
    WHERE s.`name` = stadium_name);
END; 
$$$$

SELECT udf_stadium_players_count ('Jaxworks') as `count`;
SELECT udf_stadium_players_count ('Linklinks') as `count`; 

#11. Find good playmaker by teams
DELIMITER $$$
CREATE PROCEDURE udp_find_playmaker(min_dribble_points INT, team_name VARCHAR(45))
BEGIN
DECLARE avgSpeedAllPlayers DOUBLE;
SET avgSpeedAllPlayers:= (SELECT AVG(`speed`) FROM `skills_data`);
SELECT CONCAT(p.`first_name`, ' ', p.`last_name`) AS `full_name`,
p.`age`, p.`salary`, sd.`dribbling`, sd.`speed`, tm.`name` AS 'team_name'
FROM `skills_data` AS sd
RIGHT JOIN `players` AS p
ON p.`skills_data_id` = sd.`id`
RIGHT JOIN `teams` AS tm
ON p.`team_id` = tm.`id`
WHERE tm.`name` = team_name AND sd.`dribbling` > min_dribble_points
AND  avgSpeedAllPlayers < sd.`speed`
ORDER BY sd.`speed` DESC
LIMIT 1;
END;
$$$

CALL udp_find_playmaker (20, 'Skyble');


