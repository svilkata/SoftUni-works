#2. Get Villains’ Names
SELECT v.name, COUNT(DISTINCT mv.minion_id) AS `count_of_minions`
FROM minions_db.villains AS v
LEFT JOIN minions_db.minions_villains AS mv
ON v.id = mv.villain_id
GROUP BY mv.villain_id
HAVING `count_of_minions` >15
ORDER BY `count_of_minions` DESC;

#3. Get Minion Names
SELECT CONCAT(v.`name`) AS `villain_name`
FROM minions_db.villains AS v


SELECT DISTINCT m.`name`, m.age
FROM minions_db.villains AS v
JOIN minions_db.minions_villains AS mv
ON v.id = mv.villain_id
JOIN minions_db.minions AS m
ON m.id = mv.minion_id 
WHERE v.`name` = "Carl";






