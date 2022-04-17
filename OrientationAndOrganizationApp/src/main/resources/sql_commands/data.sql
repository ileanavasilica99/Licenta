INSERT INTO person (gdpr, cnp, name, address, birth_date, gender, institutional_email,nationality, personal_email, phone_number)
VALUES ('3V8JZSK8', '299021810022' , 'John Doe1', 'Craiova, Dolj , Romania', '1999-10-10', 'MALE', 'test1@student.ucv.ro','romanian', 'johndoe1@gmail.com', 0771754323),
       ('3V8JZSK9', '299021810023' , 'John Doe2', 'Craiova, Dolj , Romania', '1999-10-10', 'MALE', 'test2@student.ucv.ro','romanian', 'johndoe2@gmail.com', 0771754323),
       ('3V8JZSK7', '299021810024' , 'John Doe3', 'Craiova, Dolj , Romania', '1999-10-10', 'MALE', 'test3@student.ucv.ro','romanian', 'johndoe3@gmail.com', 0771754323),
       ('3V8JZSK6', '299021810025' , 'John Doe4', 'Craiova, Dolj , Romania', '1999-10-10', 'MALE', 'test4@student.ucv.ro','romanian', 'johndoe4@gmail.com', 0771754323),
       ('3V8JZSK5', '299021810026' , 'John Doe5', 'Craiova, Dolj , Romania', '1999-10-10', 'MALE', 'test5@student.ucv.ro','romanian', 'johndoe5@gmail.com', 0771754323),
       ('3V8JZSK4', '299021810027' , 'John Doe6', 'Craiova, Dolj , Romania', '1999-10-10', 'MALE', 'test6@student.ucv.ro','romanian', 'johndoe6@gmail.com', 0771754323),
       ('3V8JZSK3', '299021810028' , 'John Doe7', 'Craiova, Dolj , Romania', '1999-10-10', 'MALE', 'test7@student.ucv.ro','romanian', 'johndoe7@gmail.com', 0771754323),
       ('3V8JZSK2', '299021810029' , 'John Doe8', 'Craiova, Dolj , Romania', '1999-10-10', 'MALE', 'test8@student.ucv.ro','romanian', 'johndoe8@gmail.com', 0771754323),
       ('3V8JZSK1', '299021810020' , 'John Doe9', 'Craiova, Dolj , Romania', '1999-10-10', 'MALE', 'test9@student.ucv.ro','romanian', 'johndoe9@gmail.com', 0771754323),
       ('3V8JZSK0', '299021810111' , 'John Doe10', 'Craiova, Dolj , Romania', '1999-10-10', 'MALE', 'test10@student.ucv.ro','romanian', 'johndoe10@gmail.com', 0771754323);

INSERT INTO student (id, study_program, specialization, study_year, civil_status,orphan)
VALUES  (1, 'BACHELOR', 'CEN', 4, 'SINGLE',0),
        (2, 'BACHELOR', 'CEN', 4, 'SINGLE',0),
        (3, 'BACHELOR', 'CEN', 3, 'SINGLE',0),
        (4, 'BACHELOR', 'CEN', 4, 'SINGLE',0),
        (5, 'BACHELOR', 'CEN', 4, 'SINGLE',0),
        (6, 'BACHELOR', 'CR',  4, 'SINGLE',0);

INSERT INTO teacher (id, academic_degree, department)
VALUES (7, 'Ph.D.Professor' , 'SOFTWARE_ENGINEERING'),
       (8, 'Ph.D.Professor' , 'SOFTWARE_ENGINEERING'),
       (9, 'Ph.D.Professor' , 'SOFTWARE_ENGINEERING'),
       (10, 'Ph.D.Professor' , 'AUTOMATION_AND_ELECTRONICS');