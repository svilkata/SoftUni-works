CREATE DATABASE `gamebar`;
create table `gamebar`.`employees`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY(`id`));
  
  create table `gamebar`.`categories`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY(`id`));
  
    create table `gamebar`.`products`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY(`id`));
  
INSERT INTO `gamebar`.`employees` (`id`, `first_name`, `last_name`) VALUES ('1', 'Svilen', 'Velikov');
INSERT INTO `gamebar`.`employees` (`id`, `first_name`, `last_name`) VALUES ('2', 'Ivan', 'Petrov');
INSERT INTO `gamebar`.`employees` (`id`, `first_name`, `last_name`) VALUES ('3', 'Tsvetomir', 'Velikov');

ALTER TABLE `gamebar`.`employees` 
ADD COLUMN `middle_name` VARCHAR(45) NOT NULL;

ALTER TABLE `gamebar`.`products`
ADD CONSTRAINT `fk_products_categories`
FOREIGN KEY (`category_id`) REFERENCES `gamebar`.`categories`(`id`);


ALTER TABLE `gamebar`.`employees` MODIFY COLUMN `middle_name` VARCHAR(100);
