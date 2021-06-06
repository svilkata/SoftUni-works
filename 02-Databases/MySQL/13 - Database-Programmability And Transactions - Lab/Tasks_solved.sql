DELIMITER $$
CREATE PROCEDURE usp_select_employees_by_seniority() 
BEGIN
  SELECT * 
  FROM employees
  WHERE ROUND((DATEDIFF(NOW(), hire_date) / 365.25)) < 15;
END $$

CALL usp_select_employees_by_seniority();
DROP PROCEDURE usp_select_employees_by_seniority;

DELIMITER $$
CREATE PROCEDURE usp_select_employees_by_seniority(min_years_at_work INT)
BEGIN
  SELECT first_name, last_name, hire_date,
    ROUND(DATEDIFF(NOW(),DATE(hire_date)) / 365.25,0) AS 'years'
  FROM employees
  WHERE ROUND(DATEDIFF(NOW(),DATE(hire_date)) / 365.25,0) > min_years_at_work
  ORDER BY hire_date;
END $$

CALL usp_select_employees_by_seniority(15);


DELIMITER %%
#Task 1
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(50))
RETURNS INTEGER
BEGIN
	DECLARE e_count INT;
    
    /*business logic*/
	SET `e_count` := (
    SELECT COUNT(e.`employee_id`) AS 'count' FROM `employees` AS e
	JOIN `addresses` AS a
    ON a.`address_id` = e.`address_id`
	JOIN `towns` AS t
    ON t.`town_id` = a.`town_id`
	WHERE t.`name` = town_name);
    
	RETURN `e_count`;
END;
%%

#Task 2 -Employees Promotion
DELIMITER $
CREATE PROCEDURE usp_raise_salaries (dept_name VARCHAR(45))
BEGIN
/*business logic*/
	UPDATE `employees` AS e
    JOIN `departments` AS d
    ON d.`department_id` = e.`department_id`
    SET e.`salary` = e.`salary` * 1.05
    WHERE d.`name` = dept_name;
    
    SELECT e.`first_name`, e.`salary` FROM `employees` AS e
    JOIN `departments` AS d
    ON d.`department_id` = e.`department_id`
    WHERE d.`name` = dept_name
    ORDER BY e.`first_name`, e.`salary`;
    
END $

CALL usp_raise_salaries('Finance');

DELIMITER $$
CREATE PROCEDURE usp_add_numbers (first_number INT, second_number INT, OUT result INT)
BEGIN
   SET result = first_number + second_number;
END $$

SET @answer=0;
CALL usp_add_numbers(5, 6,@answer);
SELECT @answer;


#Task 3
DELIMITER %%
CREATE PROCEDURE usp_raise_salary_by_id(id int)
BEGIN
	DECLARE does_exist INT;
	START TRANSACTION;
    UPDATE employees SET salary = salary *1.05 WHERE employee_id = id;
    
    SET does_exist := (SELECT COUNT(*) FROM employees WHERE employee_id = id);
    IF (does_exist = 1)
    THEN COMMIT;
	ELSE
		ROLLBACK;
	END IF; 
END %%

CALL usp_raise_salary_by_id(1);

#Task 4
CREATE TABLE deleted_employees(
	employee_id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	middle_name VARCHAR(20),
	job_title VARCHAR(50),
	department_id INT,
	salary DOUBLE 
);



CREATE DEFINER=`root`@`localhost` TRIGGER `tr_deleted_employees` AFTER DELETE ON `employees` FOR EACH ROW
BEGIN
	INSERT INTO deleted_employees (first_name,last_name,middle_name,job_title,department_id,salary)
	VALUES(OLD.first_name,OLD.last_name,OLD.middle_name,OLD.job_title,OLD.department_id,OLD.salary);
END;

DELETE FROM employees WHERE employee_id = 2;






CREATE DEFINER = CURRENT_USER TRIGGER `employee_AFTER_UPDATE` AFTER UPDATE ON `employees` FOR EACH ROW
BEGIN
INSERT INTO addresses_archive (old_salary, new_salary) VALUES (OLD.salary, NEW.salary)
END

CREATE DEFINER = CURRENT_USER TRIGGER `employee_AFTER_UPDATE` AFTER UPDATE ON `employees` FOR EACH ROW
BEGIN
INSERT INTO addresses_archive (old_salary, new_salary) VALUES (OLD.salary, NEW.salary)
INSERT INTO LOGS addresses_archive (old_salary, new_salary) VALUES (OLD.salary, NEW.salary)

END

