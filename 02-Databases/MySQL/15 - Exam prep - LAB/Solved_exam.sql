CREATE SCHEMA ruk_database;
USE ruk_database;

#Task 1 - Table Design
CREATE TABLE branches(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE employees(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(20) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL,
`started_on` DATE NOT NULL,
`branch_id` INT NOT NULL,

CONSTRAINT fk_employees_branches
FOREIGN KEY employees(`branch_id`)
REFERENCES branches(id)
);

CREATE TABLE clients(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`full_name` VARCHAR(50) NOT NULL,
`age` INT(11) NOT NULL
);

CREATE TABLE employees_clients(
employee_id INT(11),
client_id INT(11),

CONSTRAINT fk_ec_employees
FOREIGN KEY employees_clients(employee_id)
REFERENCES employees(id),

CONSTRAINT fk_ec_clients
FOREIGN KEY employees_clients(client_id)
REFERENCES clients(id)
);

CREATE TABLE bank_accounts(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
account_number VARCHAR(10) NOT NULL,
balance DECIMAL(10, 2) NOT NULL,
client_id INT(11) NOT NULL,

CONSTRAINT fk_ba_clients
FOREIGN KEY bank_accounts(client_id)
REFERENCES clients(id)
);

CREATE TABLE cards(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
card_number VARCHAR(19) NOT NULL,
card_status VARCHAR(7) NOT NULL,
bank_account_id INT(11) NOT NULL,

CONSTRAINT fk_cards_ba
FOREIGN KEY cards(bank_account_id)
REFERENCES bank_accounts(id)
);

#Task 2 - Insert
INSERT INTO cards(card_number, card_status, bank_account_id)
(
SELECT REVERSE(full_name), 'Active', id
FROM clients
WHERE id>=191 AND id<=200
);

/*#Task 2 - Insert - another way
INSERT INTO cards(card_status, card_number, bank_account_id)
(
SELECT (
	CASE
		WHEN id BETWEEN 191 AND 199 THEN 'Active'
		WHEN id BETWEEN 200 AND 299 THEN 'Inactive'
		WHEN id BETWEEN 300 AND 500 THEN 'Deleted'
    END
) AS customs_status, REVERSE(full_name), id
FROM clients
);*/

#Task 3 - Update - version in which we miss the employees who have 0 clients
UPDATE employees_clients AS ec
SET ec.employee_id = 
(
	SELECT ec.employee_id
	GROUP BY ec.employee_id
	ORDER BY COUNT(ec.employee_id) ASC, ec.employee_id ASC
	LIMIT 1
)
WHERE ec.employee_id = ec.client_id;

#Task 3 - Update - version in which we have employees with 0 clients - UPDATE JOIN
UPDATE employees_clients AS ec
SET ec.employee_id =
(
	SELECT COUNT(e.id) FROM employees AS e
	LEFT JOIN (SELECT * from employees_clients) AS emcl
	ON emcl.employee_id = e.id
	GROUP BY e.id
	ORDER BY COUNT(e.id) ASC, e.id ASC
	LIMIT 1
)
WHERE ec.employee_id = ec.client_id;

#Task 4 - Option 1
DELETE emp FROM employees AS emp
LEFT JOIN employees_clients AS ec
ON ec.employee_id = emp.id
WHERE ec.client_id IS NULL;

#Task 4 - Option 2
DELETE FROM employees WHERE id = (
	SELECT emp.id FROM (SELECT * FROM employees) AS emp
    LEFT JOIN employees_clients AS ec
    ON ec.employee_id = emp.id
    WHERE ec.client_id IS NULL
);

#Task 5
SELECT id, full_name FROM clients ORDER BY id ASC;


#Task 6 - Newbies
SELECT id,
CONCAT(first_name, ' ', last_name) AS 'full_name',
CONCAT('$', salary),
started_on
FROM employees
WHERE salary >= 100000 AND started_on >= '2018-01-01'
ORDER BY salary DESC, id ASC;

#Task 7 - Cards against Humanity
SELECT c.id,
CONCAT(c.card_number, ' : ', cl.full_name) AS card_token
FROM cards AS c
JOIN bank_accounts AS ba
ON ba.id = c.bank_account_id
JOIN clients AS cl
ON ba.client_id = cl.id
ORDER BY c.id DESC;

#Task 8 - Top 5 Employees
SELECT CONCAT(emp.first_name, ' ', emp.last_name) AS 'name',
emp.started_on,
COUNT(ec.employee_id) AS count_of_clients
FROM employees AS emp
LEFT JOIN employees_clients AS ec
ON ec.employee_id = emp.id
GROUP BY ec.employee_id
ORDER BY count_of_clients DESC, emp.id ASC
LIMIT 5;

#Task 9 - Branch cards
SELECT b.`name` AS 'name',
COUNT(c.id) AS 'count_of_cards'
FROM branches AS b
LEFT JOIN employees AS e
ON e.branch_id = b.id
LEFT JOIN employees_clients AS ec
ON ec.employee_id = e.id
LEFT JOIN clients AS cl
ON cl.id = ec.client_id
LEFT JOIN bank_accounts AS ba
ON ba.client_id = cl.id
LEFT JOIN cards AS c
ON c.bank_account_id = ba.id
GROUP BY b.id
ORDER BY count_of_cards DESC, b.`name` ASC;

#Task 10 - Extract client cards count
DELIMITER %%
CREATE FUNCTION `udf_client_cards_count` (name_param VARCHAR(30))
RETURNS INTEGER
DETERMINISTIC
BEGIN
	RETURN (SELECT COUNT(c.id) AS 'cards' FROM cards AS c
	LEFT JOIN bank_accounts AS ba
	ON ba.id = c.bank_account_id
	LEFT JOIN clients AS cl
	ON ba.client_id = cl.id
	WHERE cl.full_name = name_param);
   
END; %%

SELECT c.full_name, udf_client_cards_count('Baxy David') as `cards` FROM clients c
WHERE c.full_name = 'Baxy David';

#Task 11 - Extract Client Info
DELIMITER %%
CREATE PROCEDURE `udp_clientinfo`(x VARCHAR(30))
BEGIN
	SELECT cl.full_name, cl.age, ba.account_number, 
	CONCAT('$', ba.balance) AS 'balance'
	FROM clients AS cl
	JOIN bank_accounts AS ba
	ON ba.client_id = cl.id
	WHERE cl.full_name = x;
END;
%%

CALL udp_clientinfo('Hunter Wesgate');




