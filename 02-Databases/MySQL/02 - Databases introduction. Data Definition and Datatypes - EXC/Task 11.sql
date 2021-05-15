CREATE DATABASE `Movies`;
USE `Movies`;

CREATE TABLE `directors`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`director_name` CHAR(50) NOT NULL,
`notes` TEXT
);

CREATE TABLE `genres`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`genre_name` CHAR(50) NOT NULL,
`notes` TEXT
);

CREATE TABLE `categories`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`category_name` CHAR(50) NOT NULL,
`notes` TEXT
);

CREATE TABLE `movies`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`title` CHAR(45) NOT NULL,
`director_id` INT NOT NULL,
`copyright_year` INT,
`length` INT NOT NULL,
`genre_id` INT NOT NULL,
`category_id` INT NOT NULL,
`rating` INT,
`notes` TEXT,
CONSTRAINT `fk_movies_directors` FOREIGN KEY (`director_id`) REFERENCES `directors`(`id`),
CONSTRAINT `fk_movies_genres` FOREIGN KEY (`genre_id`) REFERENCES `genres`(`id`),
CONSTRAINT `fk_movies_categories` FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`)
);

INSERT INTO `directors`
(`id`,`director_name`,`notes`)
VALUES (1,'Ivanovich',NULL),
(2,'Mile Kitich', 'i kakvo sega'),
(3,'Zoran pile', 'i kakvo sled malko'),
(4,'Ratatui', NULL),
(5,'Ivana', 'ai malko chalga');

INSERT INTO `categories`
(`id`,`category_name`,`notes`)
VALUES
(1,'First class','Qk film'),
(2,'New films 2021','Qk film'),
(3,'Films of 2020','Qk film'),
(4,'Films of 2019',NULL),
(5,'Films of 2018','Qk film');

INSERT INTO `genres`
(`id`,`genre_name`,`notes`)
VALUES
(1,'Romance','Qk film'),
(2,'Horror','Qk film'),
(3,'Drama','Qk film'),
(4,'Action',NULL),
(5,'Bibliography','Qk film');

INSERT INTO `movies`
(`id`,`title`,`director_id`,`copyright_year`,`length`,`genre_id`,`category_id`,`rating`,`notes`)
VALUES
(1,'Tarzan',1,NULL,120,1,5,9,'kakvo da pisha tuk sega'),
(2,'Gordost i predrazsydatsi',4,2010,130,2,4,NULL,NULL),
(3,'Pretty little things',5,2017,90,2,3,10,'po-dobre da ne pisha tuk'),
(4,'Pretty woman',3,2000,85,2,2,9,NULL),
(5,'Sex and the city',2,2004,40,5,1,10,'i naj-nakraq');










