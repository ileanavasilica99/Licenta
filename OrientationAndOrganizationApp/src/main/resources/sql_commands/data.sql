insert into students_evidence (specialization, year, student_group, student_subgroup, students_number)
values ('CE', '4', 'S1', 'A', 13),
       ('CE', '4', 'S1', 'B', 10),
       ('CE', '4', 'S2', 'A', 11),
       ('CE', '4', 'S2', 'B', 12),
       ('CE', '4', 'H1', 'A', 10),
       ('CE', '4', 'H1', 'B', 10),
       ('CE', '3', 'CEN3.1', 'A', 13),
       ('CE', '3', 'CEN3.1', 'B', 13),
       ('CE', '3', 'CEN3.2', 'A', 10),
       ('CE', '3', 'CEN3.2', 'B', 13),
       ('CE', '3', 'CEN3.3', 'A', 14),
       ('CE', '3', 'CEN3.3', 'B', 14),
       ('CE', '2', 'CEN2.1', 'A', 13),
       ('CE', '2', 'CEN2.1', 'B', 13),
       ('CE', '2', 'CEN2.2', 'A', 13),
       ('CE', '2', 'CEN2.2', 'B', 13),
       ('CE', '2', 'CEN2.3', 'A', 13),
       ('CE', '2', 'CEN2.3', 'B', 13),
       ('CE', '1', 'CEN1.1', 'A', 14),
       ('CE', '1', 'CEN1.1', 'B', 13),
       ('CE', '1', 'CEN1.2', 'A', 14),
       ('CE', '1', 'CEN1.2', 'B', 14),
       ('CE', '1', 'CEN1.3', 'A', 14),
       ('CE', '1', 'CEN1.3', 'B', 12);

insert into user (first_name, last_name, cnp, address, date_of_birth, email, phone_number, role, password)
values ('John', 'Doe1', '1223231232122', 'Craiova, Romania', '1999-02-18', 'johndoe1@student.com', '0771734234',
        'STUDENT', '3V8JZS99'),
       ('John', 'Doe2', '1223231232124', 'Craiova, Romania', '1999-02-18', 'johndoe2@student.com', '0771734234',
        'STUDENT', '3V8JZS99'),
       ('John', 'Doe3', '1223231232125', 'Craiova, Romania', '1999-02-18', 'johndoe3@student.com', '0771734234',
        'STUDENT', '3V8JZS99'),
       ('John', 'Doe4', '1223231232126', 'Craiova, Romania', '1999-02-18', 'johndoe4@student.com', '0771734234',
        'STUDENT', '3V8JZS99'),
       ('John', 'Doe5', '1223231232127', 'Craiova, Romania', '1999-02-18', 'johndoe5@student.com', '0771734234',
        'STUDENT', '3V8JZS99'),
       ('John', 'Doe6', '1223231232128', 'Craiova, Romania', '1999-02-18', 'johndoe6@student.com', '0771734234',
        'STUDENT', '3V8JZS99'),
       ('John', 'Doe7', '1223231232129', 'Craiova, Romania', '1999-02-18', 'johndoe7@student.com', '0771734234',
        'STUDENT', '3V8JZS99'),
       ('John', 'Doe8', '1223231232131', 'Craiova, Romania', '1999-02-18', 'johndoe8@student.com', '0771734234',
        'STUDENT', '3V8JZS99'),
       ('John', 'Doe9', '1223231232133', 'Craiova, Romania', '1999-02-18', 'johndoe9@student.com', '0771734234',
        'STUDENT', '3V8JZS99'),
       ('John', 'Doe10', '1223231232143', 'Craiova, Romania', '1999-02-18', 'johndoe10@student.com', '0771734234',
        'STUDENT', '3V8JZS99');

insert into user (first_name, last_name, cnp, address, date_of_birth, email, phone_number, role, password)
values ('Shara', 'Smith1', '1222222222222', 'Craiova, Romania', '1970-02-18', 'sharasmith1@student.com', '0771734234',
        'TEACHER', '3V8JZS70'),
       ('Shara', 'Smith2', '1222222222223', 'Craiova, Romania', '1970-02-18', 'sharasmith2@student.com', '0771734234',
        'TEACHER', '3V8JZS70'),
       ('Shara', 'Smith3', '1222222222224', 'Craiova, Romania', '1970-02-18', 'sharasmith3@student.com', '0771734234',
        'TEACHER', '3V8JZS70'),
       ('Shara', 'Smith4', '1222222222225', 'Craiova, Romania', '1970-02-18', 'sharasmith4@student.com', '0771734234',
        'TEACHER', '3V8JZS70'),
       ('Shara', 'Smith5', '1222222222226', 'Craiova, Romania', '1970-02-18', 'sharasmith5@student.com', '0771734234',
        'TEACHER', '3V8JZS70'),
       ('Shara', 'Smith6', '1222222222227', 'Craiova, Romania', '1970-02-18', 'sharasmith6@student.com', '0771734234',
        'TEACHER', '3V8JZS70'),
       ('Shara', 'Smith7', '1222222222228', 'Craiova, Romania', '1970-02-18', 'sharasmith7@student.com', '0771734234',
        'TEACHER', '3V8JZS70'),
       ('Shara', 'Smith8', '1222222222229', 'Craiova, Romania', '1970-02-18', 'sharasmith8@student.com', '0771734234',
        'TEACHER', '3V8JZS70'),
       ('Shara', 'Smith9', '1222222222210', 'Craiova, Romania', '1970-02-18', 'sharasmith9@student.com', '0771734234',
        'TEACHER', '3V8JZS70'),
       ('Shara', 'Smith10', '1222222222233', 'Craiova, Romania', '1970-02-18', 'sharasmith10@student.com', '0771734234',
        'TEACHER', '3V8JZS70');

insert into teacher (id, department, academic_degree)
values (11, 'SOFTWARE_ENGINEERING', 'prof dr ing'),
       (12, 'SOFTWARE_ENGINEERING', 'prof dr ing'),
       (13, 'SOFTWARE_ENGINEERING', 'dr ing'),
       (14, 'AUTOMATION_AND_ELECTRONICS', 'prof dr ing'),
       (15, 'AUTOMATION_AND_ELECTRONICS', 'prof dr ing'),
       (16, 'MECHATRONICS_AND_ROBOTICS', 'prof dr ing');

insert into student (id, specialization, study_year, student_group, student_subgroup)
values (1, 'CE', '4', 'S1', 'A'),
       (2, 'CE', '4', 'S1', 'A'),
       (3, 'CE', '4', 'S1', 'A'),
       (4, 'CE', '4', 'S1', 'A'),
       (5, 'CE', '4', 'S1', 'A'),
       (6, 'CE', '4', 'S1', 'A'),
       (7, 'CE', '4', 'S1', 'A'),
       (8, 'CE', '4', 'S1', 'A'),
       (9, 'CE', '4', 'S1', 'A'),
       (10, 'CE', '4', 'S1', 'A');

insert into course (name, year, teacher_id)
values ('Software Project Management', 4, 11),
       ('Translator Design', 4, 12),
       ('Sisteme numerice de conducere', 4, 14);

insert into activity (course_id, activity_type, teacher_id, specialization, student_group, student_subgroup, day,
                      start_hour, end_hour, classroom_type)
values (1, 'LABORATORY', 13, 'CE', 'S1', 'A', 'MONDAY', '08:00', '10:00', 'INFORMATICS_LAB'),
       (1, 'LABORATORY', 13, 'CE', 'S1', 'B', 'MONDAY', '12:00', '14:00', 'INFORMATICS_LAB'),
       (1, 'COURSE', 11, 'CE', '-', '-', 'MONDAY', '16:00', '18:00', 'AULA');

-- insert first classrooms in database N3 (id=1) and AK1(id=2)

insert into schedule (classroom_id, day, start_hour, end_hour)
values (1, 'MONDAY', '08:00', '10:00'),
       (1, 'MONDAY', '12:00', '14:00'),
       (2, 'MONDAY', '16:00', '18:00');