INSERT INTO STUDENTS(ID, NAME, AGE)
VALUES (1, 'Pesho', 33),
       (2, 'Ana', 23);

INSERT INTO COURSES(ID, NAME, ENABLED, PRICE)
VALUES (1, 'Spring for dummies', false, 50),
       (2, 'Spring for pros', true, 100);

INSERT INTO ORDERS(ID, COURSE_ID, STUDENT_ID)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 2, 2);