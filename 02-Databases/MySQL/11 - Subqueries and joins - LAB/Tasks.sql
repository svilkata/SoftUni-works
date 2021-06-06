#Tests
CREATE TABLE auto_filled AS
SELECT e.`first_name`, d.`name` AS 'dept_name'
FROM `employees` AS e
INNER JOIN `departments` AS d
ON e.`department_id` = d.`department_id`;

CREATE INDEX
ix_users_first_name_last_name
on `users`(`first_name`, `last_name`);




#Task 1
SELECT e.employee_id, CONCAT(first_name, " ", last_name) AS `full_name`, d.department_id, d.name
FROM employees AS e
RIGHT JOIN departments AS d 
ON d.manager_id = e.employee_id 
ORDER BY e.employee_id LIMIT 5;

# Task 2
SELECT t.`town_id`, t.`name`, a.`address_text`
FROM `addresses` AS a
JOIN `towns` AS t
ON a.`town_id` = t.`town_id` AND (t.`name` = 'San Francisco' OR t.`name` = 'Sofia' OR t.`name` = 'Carnation')
ORDER BY a.`town_id` ASC, a.`address_id`;

#Task 3
SELECT `employee_id`, `first_name`, `last_name`, `department_id`, `salary`  FROM `employees`
WHERE `manager_id` IS NULL;


#Task 4
SELECT COUNT(e.employee_id) AS `count` 
FROM employees AS e
WHERE e.salary >
(
SELECT AVG(salary) AS 'average_salary' FROM employees
);



