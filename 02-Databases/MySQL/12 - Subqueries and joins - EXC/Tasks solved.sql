#Task 1
SELECT e.`employee_id`, e.`job_title`, e.`address_id`, a.`address_text`
FROM `employees` AS e
JOIN `addresses` AS a
ON e.`address_id` = a.`address_id`
ORDER BY e.`address_id` ASC
LIMIT 5;

#Task 2
SELECT e.`first_name`, e.`last_name`, t.`name`, a.`address_text`
FROM `employees` AS e
JOIN `addresses` AS a
ON e.`address_id` = a.`address_id`
JOIN `towns` AS t
ON a.`town_id` = t.`town_id`
ORDER BY e.`first_name` ASC, e.`last_name` ASC
LIMIT 5;

#Task 3
SELECT e.`employee_id`, e.`first_name`, e.`last_name`, d.`name`
FROM `employees` AS e
JOIN `departments` AS d
ON e.`department_id` = d.`department_id` AND d.`name` = 'Sales'
ORDER BY e.`employee_id` DESC;

#Task 4
SELECT e.`employee_id`, e.`first_name`, e.`salary`, d.`name`
FROM `employees` AS e
JOIN `departments` AS d
ON e.`department_id` = d.`department_id` AND e.`salary` > 15000
ORDER BY e.`department_id` DESC, e.`salary` DESC
LIMIT 5;

#Task 5
SELECT e.`employee_id`, e.`first_name`
FROM `employees` AS e
LEFT JOIN `employees_projects` AS ep
ON e.`employee_id` = ep.`employee_id`
WHERE ep.`project_id` IS NULL
ORDER BY e.`employee_id` DESC
LIMIT 3;

#Task 6
SELECT e.`first_name`, e.`last_name`, e.`hire_date`, d.`name` AS 'dept_name'
FROM `employees` AS e
INNER JOIN `departments` AS d
ON e.`department_id` = d.`department_id`
WHERE e.`hire_date` >= '1999-01-02' AND (d.`name` = 'Sales' OR d.`name` = 'Finance')
ORDER BY e.`hire_date` ASC;

#Task 7
SELECT e.`employee_id`, e.`first_name`, p.`name` AS 'project_name'
FROM `employees` AS e
JOIN `employees_projects` AS ep
ON e.`employee_id` = ep.`employee_id`
JOIN `projects` AS p
ON p.`project_id` = ep.`project_id`
WHERE p.`start_date` >= '2002-08-14' AND p.`end_date` IS NULL
ORDER BY e.`first_name` ASC, `project_name` ASC
LIMIT 5;

#Task 8
SELECT e.`employee_id`, e.`first_name`, 
(
CASE
	WHEN YEAR(p.`start_date`) > 2004 
    THEN NULL
    ELSE p.`name`
END
)
AS 'project_name'
FROM `employees` AS e
JOIN `employees_projects` AS ep
ON e.`employee_id` = ep.`employee_id`
JOIN `projects` AS p
ON p.`project_id` = ep.`project_id`
WHERE e.`employee_id` = 24
ORDER BY `project_name`;

#Task 9
SELECT e.`employee_id`, e.`first_name`, m.`employee_id` AS 'manager_id', m.`first_name` AS 'manager_name'
FROM `employees` AS e
JOIN `employees` AS m
ON e.`manager_id` = m.`employee_id`
WHERE e.`manager_id` IN (3, 7)
ORDER BY e.`first_name`;

#Task 10
SELECT e.`employee_id`, 
CONCAT(e.`first_name`, ' ', e.`last_name`) AS 'employee_name',
CONCAT(m.`first_name`, ' ', m.`last_name`) AS 'manager_name',
d.`name` AS 'department_name'
FROM `employees` AS e
JOIN `employees` AS m
ON e.`manager_id` = m.`employee_id`
JOIN `departments` AS d
ON d.`department_id` = e.`department_id`
ORDER BY e.`employee_id`
LIMIT 5;

#Task 11
CREATE VIEW `my_view` AS 
SELECT AVG(e.`salary`) AS 'average_department_salary' FROM `employees` AS e GROUP BY e.`department_id`;

SELECT 
MIN(mv.`average_department_salary`) AS 'min_average_salary'
FROM `my_view` AS mv;


#Task 12
SELECT c.`country_code`, m.`mountain_range`, p.`peak_name`, p.`elevation`
FROM `countries` AS c
JOIN `mountains_countries` AS mc
ON c.`country_code` = mc.`country_code`
JOIN `mountains` AS m
ON mc.`mountain_id` = m.`id`
JOIN `peaks` AS p
ON m.`id` = p.`mountain_id`
WHERE c.`country_code` = 'BG' AND p.`elevation` > 2835
ORDER BY p.`elevation` DESC;

#Task 13
SELECT c.`country_code`, COUNT(m.`mountain_range`) AS 'mountain_range'
FROM `countries` AS c
JOIN `mountains_countries` AS mc
ON c.`country_code` = mc.`country_code`
JOIN `mountains` AS m
ON mc.`mountain_id` = m.`id`
WHERE c.`country_name` IN ('United States', 'Russia', 'Bulgaria')
GROUP BY c.`country_name`;

#Task 14
SELECT 
c.`country_name`, r.`river_name`
FROM `continents` AS cnt
JOIN `countries` AS c
ON c.`continent_code` = cnt.`continent_code`
LEFT JOIN `countries_rivers` AS cr
ON c.`country_code` = cr.`country_code`
LEFT JOIN `rivers` AS r
ON cr.`river_id` = r.`id`
WHERE cnt.`continent_name` = 'Africa'
ORDER BY c.`country_name` ASC
LIMIT 5;

#Task 15
CREATE VIEW `first` AS
SELECT c.`continent_code`, c.`currency_code`,
(
	COUNT(c.`currency_code`)
)
AS 'currency_usage'
#currency_usage - how many countries are using the most frequent currency
FROM `countries` AS c
GROUP BY c.`continent_code`;




#Task 16 - countries without mountains
CREATE VIEW `Sechenie` AS 
SELECT 
c.`country_name`
FROM `countries` AS c
JOIN `mountains_countries` AS mc
ON c.`country_code` = mc.`country_code`
GROUP BY c.`country_name`;

SELECT 
(250 - COUNT(s.`country_name`) ) AS 'country_count'
FROM `Sechenie` AS s;


#Task 17
SELECT c.`country_name`, 
MAX(p.`elevation`) AS 'highest_peak_elevation',
MAX(r.`length`) AS 'longest_river_length'

FROM `countries` AS c
LEFT JOIN `mountains_countries` AS mc
ON c.`country_code` = mc.`country_code`
JOIN `mountains` AS m
ON mc.`mountain_id` = m.`id`
JOIN `peaks` AS p
ON m.`id` = p.`mountain_id`

LEFT JOIN `countries_rivers` AS cr
ON c.`country_code` = cr.`country_code`
JOIN `rivers` AS r
ON cr.`river_id` = r.`id`

GROUP BY c.`country_code`
ORDER BY `highest_peak_elevation` DESC, `longest_river_length` DESC, c.`country_name` ASC
LIMIT 5;




