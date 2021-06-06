#Task 1 - Employees with Salary Above 35000
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000 ()
BEGIN
	SELECT `first_name`, `last_name`
	FROM `employees`
	WHERE `salary` > 35000
	ORDER BY `first_name`, `last_name`, `employee_id`;
END; $$

#Task 2 - Employees with Salary Above Number
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above (`min_salary` DECIMAL(19, 4))
BEGIN
	SELECT `first_name`, `last_name`
	FROM `employees`
	WHERE `salary` >= `min_salary`
	ORDER BY `first_name`, `last_name`, `employee_id`;
END; $$

CALL usp_get_employees_salary_above(80000);

#Task 3 - Town Names Starting With
DELIMITER %%%
CREATE PROCEDURE usp_get_towns_starting_with (starts_with VARCHAR(20))
BEGIN
	SELECT `name` FROM `towns`
	WHERE `name` LIKE concat(starts_with, '%')
    ORDER BY `name`;
END; %%%

CALL usp_get_towns_starting_with('b');

#Task 4 - Employees from Town
DELIMITER %%
CREATE PROCEDURE usp_get_employees_from_town (starts_with VARCHAR(20))
BEGIN
	SELECT e.`first_name`, e.`last_name`
	FROM `employees` AS e
	JOIN `addresses` AS a
	ON a.`address_id` = e.`address_id`
	JOIN `towns` AS t
	ON t.`town_id` = a.`town_id`
	WHERE t.`name` LIKE concat(starts_with, '%')
	ORDER BY e.`first_name`, e.`last_name`, e.`employee_id`;
END; %%

CALL usp_get_employees_from_town('Sof');

#Task 5 - Salary Level Function
DELIMITER $$$$
CREATE FUNCTION ufn_get_salary_level(salary_emp DECIMAL)
RETURNS VARCHAR(10)
DETERMINISTIC
BEGIN
	DECLARE result VARCHAR(10);
    
    IF(salary_emp < 30000) THEN SET result := 'Low';
    ELSEIF (salary_emp <= 50000) THEN SET result := 'Average';
    ELSE SET result := 'High';    
    END IF;   
	
	RETURN result;
END; 
$$$$

SELECT ufn_get_salary_level(51000.0);

DROP FUNCTION ufn_get_salary_level;

#Task 6 - Employees by Salary Level
DELIMITER %%
CREATE PROCEDURE usp_get_employees_by_salary_level (salary_level VARCHAR(10))
BEGIN
	SELECT `first_name`, `last_name`
	FROM `employees`
	WHERE LOWER(ufn_get_salary_level(`salary`)) = salary_level
	ORDER BY `first_name` DESC, `last_name` DESC;
END; %%

CALL usp_get_employees_by_salary_level('high');

#Task 7 - Define Function
DELIMITER %%
CREATE FUNCTION ufn_IsWordComprised(setOfLetters VARCHAR (50), word VARCHAR (50)) 
RETURNS INT
deterministic
BEGIN
     DECLARE index_letter INT;
     DECLARE length_word INT;
     DECLARE letter CHAR(1);
     
     SET index_letter := 1;
     SET length_word := CHAR_LENGTH(word);      

     WHILE (index_letter <= length_word)
     DO
          SET letter := SUBSTRING(word, index_letter, 1);
          
          IF (LOCATE(letter, setOfLetters) > 0) THEN SET index_letter := index_letter + 1;
          ELSE
             RETURN 0;
		  END IF;
     END WHILE;
     
     RETURN 1;
END;
%%

SELECT ufn_IsWordComprised('oistmiahf', 'Sofia');
SELECT ufn_IsWordComprised('oistmiahf', 'halves');

#Task 8 - Find Full Name
DELIMITER %%
CREATE PROCEDURE usp_get_holders_full_name ()
BEGIN
	SELECT CONCAT(`first_name`, ' ', `last_name`) AS 'full_name' 
	FROM `account_holders`
	ORDER BY `full_name` ASC, `id` ASC;
END;%%

CALL usp_get_holders_full_name ();

#Task 9 - People with Balance Higher Than
DELIMITER %%
CREATE PROCEDURE usp_get_holders_with_balance_higher_than (salary_level DECIMAL)
BEGIN
	SELECT ah.`first_name`, ah.`last_name`
	FROM `account_holders` AS ah
	JOIN `accounts` AS a
	ON ah.`id` = a.`account_holder_id`
	GROUP BY a.`account_holder_id`
	HAVING SUM(a.`balance`) > salary_level
	ORDER BY a.`account_holder_id` ASC;
END;%%

CALL usp_get_holders_with_balance_higher_than (20000);

#Task 10 - Future Value Function
DELIMITER $$$$
CREATE FUNCTION ufn_calculate_future_value(sum DOUBLE, interest_rate DECIMAL(19, 4), years INT)
RETURNS DECIMAL(19, 4)
DETERMINISTIC
BEGIN
    RETURN sum * POW((1 + interest_rate), years);
END; 
$$$$

SELECT ufn_calculate_future_value(1000, 0.1, 5);

SELECT 1000 * POW((1 + 0.1), 5);

#Task 11 - Calculating Interest
DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account(account_id INT, interest_rate DECIMAL(19, 4))
BEGIN
	SELECT a.`id`, ah.`first_name`, ah.`last_name`, 
	a.`balance` AS 'current_balance',
	ufn_calculate_future_value(a.`balance`, interest_rate, 5) AS 'balance_in_5_years'
	FROM `account_holders` AS ah
	JOIN `accounts` AS a
	ON ah.`id` = a.`account_holder_id`
	WHERE a.`id` = account_id
	GROUP BY a.`account_holder_id`;
END;
$$

CALL usp_calculate_future_value_for_account(1, 0.1);

#Task 12 - Deposit Money
DELIMITER %%
CREATE PROCEDURE usp_deposit_money(acc_id INT, money_amount DECIMAL(19, 4))
BEGIN
	START TRANSACTION;
    IF (money_amount <= 0) 
		THEN ROLLBACK;
	ELSE 
		UPDATE accounts 
        SET balance = balance + money_amount
        WHERE id = acc_id;
        
		COMMIT;    
    END IF;

END;
%%

CALL usp_deposit_money(1, 100);

#Tasl 13 - Withdraw Money
DELIMITER %%
CREATE PROCEDURE usp_withdraw_money(acc_id INT, money_amount DECIMAL(19, 4))
BEGIN
	START TRANSACTION;  
  
    IF (money_amount <= 0 OR ((SELECT balance FROM accounts WHERE id = acc_id) - money_amount < 0)) 
		THEN ROLLBACK;
	ELSE 
		UPDATE accounts 
		SET balance = balance - money_amount
		WHERE id = acc_id;
        
		COMMIT;    
    END IF;

END;
%%

CALL usp_withdraw_money(1, 10);


#Task 14 - Money Transfer
DELIMITER %%
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19, 4))
BEGIN
	START TRANSACTION;
    IF((SELECT id FROM accounts WHERE id = from_account_id) IS NULL 
    OR (SELECT id FROM accounts WHERE id = to_account_id) IS NULL
    OR from_account_id = to_account_id)
    THEN ROLLBACK;
	ELSE 
		IF (amount <= 0 OR ((SELECT balance FROM accounts WHERE id = from_account_id) - amount < 0)) 
		THEN ROLLBACK;
        ELSE
			UPDATE accounts 
			SET balance = balance - amount
			WHERE id = from_account_id;
            
            UPDATE accounts 
			SET balance = balance + amount
			WHERE id = to_account_id;
        
			COMMIT;
		END IF;
    END IF;
END;
%%

CALL usp_transfer_money(5, 1, 100);

#Task 15 - Log Accounts Trigger
CREATE TABLE logs(
	log_id INT PRIMARY KEY AUTO_INCREMENT,
	account_id INT,
    old_sum DECIMAL(19, 4),
    new_sum DECIMAL(19, 4) 
);

CREATE DEFINER=`root`@`localhost` TRIGGER `accounts_AFTER_UPDATE` AFTER UPDATE ON `accounts` FOR EACH ROW BEGIN
INSERT INTO `logs` (`account_id`, `old_sum`, `new_sum`) VALUES (OLD.`id`, OLD.`balance`, NEW.`balance`);
END

