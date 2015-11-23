INSERT INTO group_table (ID, NAME) VALUES (1, '10305H');
INSERT INTO group_table (ID, NAME) VALUES (2, '10305S');

INSERT INTO SECTION (ID, NAME) VALUES (1, 'C.E.');
INSERT INTO SECTION (ID, NAME) VALUES (2, 'C.R.');

INSERT INTO SUBGROUP (ID, NAME) VALUES (1, 'A');
INSERT INTO SUBGROUP (ID, NAME) VALUES (2, 'B');
INSERT INTO SUBGROUP (ID, NAME) VALUES (3, 'C');
INSERT INTO SUBGROUP (ID, NAME) VALUES (4, 'D');

INSERT INTO STATE (ID, NAME) VALUES (1, 'Active');
INSERT INTO STATE (ID, NAME) VALUES (2, 'Deleted');
INSERT INTO STATE (ID, NAME) VALUES (3, 'Inactive');
INSERT INTO STATE (ID, NAME) VALUES (4, 'Locked');

INSERT INTO TYPE_TABLE (ID, NAME) VALUES (1, 'STUDENT');
INSERT INTO TYPE_TABLE (ID, NAME) VALUES (2, 'PROFESSOR');
INSERT INTO TYPE_TABLE (ID, NAME) VALUES (3, 'ADMIN');

INSERT INTO YEAR (ID, VALUE) VALUES (1, 1);
INSERT INTO YEAR (ID, VALUE) VALUES (2, 2);
INSERT INTO YEAR (ID, VALUE) VALUES (3, 3);
INSERT INTO YEAR (ID, VALUE) VALUES (4, 4);

INSERT INTO SEMESTER (ID, VALUE) VALUES (1, 1);
INSERT INTO SEMESTER (ID, VALUE) VALUES (2, 2);

INSERT INTO DAY (ID, VALUE) VALUES (1, 1);
INSERT INTO DAY (ID, VALUE) VALUES (2, 2);
INSERT INTO DAY (ID, VALUE) VALUES (3, 3);
INSERT INTO DAY (ID, VALUE) VALUES (4, 4);
INSERT INTO DAY (ID, VALUE) VALUES (5, 5);

INSERT INTO HOUR (ID, VALUE) VALUES (1, 8);
INSERT INTO HOUR (ID, VALUE) VALUES (2, 10);
INSERT INTO HOUR (ID, VALUE) VALUES (3, 12);
INSERT INTO HOUR (ID, VALUE) VALUES (4, 14);
INSERT INTO HOUR (ID, VALUE) VALUES (5, 16);
INSERT INTO HOUR (ID, VALUE) VALUES (6, 18);
INSERT INTO HOUR (ID, VALUE) VALUES (7, 20);

INSERT INTO ROOM (ID, NAME) VALUES (1, 'S1');
INSERT INTO ROOM (ID, NAME) VALUES (2, 'S2');
INSERT INTO ROOM (ID, NAME) VALUES (3, 'S3');

INSERT INTO WEEKLY_OCCURRENCE (ID, NAME) VALUES (1, 'Odd Week');
INSERT INTO WEEKLY_OCCURRENCE (ID, NAME) VALUES (2, 'Even Week');
INSERT INTO WEEKLY_OCCURRENCE (ID, NAME) VALUES (3, 'Every Week');


INSERT INTO PERSON (PNC, FIRST_NAME, LAST_NAME, EMAIL)
VALUES ('0000000000000', 'admin', 'admin', 'admin@software.ucv.ro');
INSERT INTO user (USERNAME, PASSWORD, STATE_ID, TYPE_ID)
VALUES ('0000000000000', '$2a$10$csrGgdaao3nEiNuYDXm7qO0BHgnP2eUJeJFOHZQT9M0QEwqQupo8i', 1, 3);

INSERT INTO PERSON (PNC, FIRST_NAME, LAST_NAME, EMAIL)
VALUES ('1940826160041', 'Georgian', 'Vladutu', 'vladutu_georgian_4d@yahoo.com');
INSERT INTO STUDENT (PNC, SECTION_ID, GROUP_ID, SUBGROUP_ID, YEAR_ID, SEMESTER_ID)
VALUES ('1940826160041', 1, 1, 3, 3, 1);

INSERT INTO PERSON (PNC, FIRST_NAME, LAST_NAME, EMAIL)
VALUES ('2940816160012', 'Ramona', 'Foamete', 'foamete_ramona@yahoo.com');
INSERT INTO STUDENT (PNC, SECTION_ID, GROUP_ID, SUBGROUP_ID, YEAR_ID, SEMESTER_ID)
VALUES ('2940816160012', 1, 1, 3, 3, 1);


INSERT INTO PERSON (PNC, FIRST_NAME, LAST_NAME, EMAIL)
VALUES ('1550101160043', 'Stefan', 'Udristoiu', 'stefan_udristoiu@software.ucv.ro');
INSERT INTO PROFESSOR (PNC, POSITION, OFFICE)
VALUES ('1550101160043', 'Asist.', '315');

INSERT INTO PERSON (PNC, FIRST_NAME, LAST_NAME, EMAIL)
VALUES ('1550102160043', 'Marius', 'Brezovan', 'marius_brezovan@software.ucv.ro');
INSERT INTO PROFESSOR (PNC, POSITION, OFFICE)
VALUES ('1550102160043', 'Prof.', '313');

INSERT INTO LABORATORY (ID, NAME, FROM_ID, TO_ID, PROFESSOR_PNC, ROOM_ID, DAY_ID, SECTION_ID, GROUP_ID, SUBGROUP_ID, YEAR_ID, SEMESTER_ID, WEEKLY_OCCURRENCE_ID)
VALUES (1, 'O.S.', 2, 3, '1550101160043', 2, 4, 1, 1, 3, 3, 1, 3);

INSERT INTO LABORATORY (ID, NAME, FROM_ID, TO_ID, PROFESSOR_PNC, ROOM_ID, DAY_ID, SECTION_ID, GROUP_ID, SUBGROUP_ID, YEAR_ID, SEMESTER_ID, WEEKLY_OCCURRENCE_ID)
VALUES (2, 'C.S.O.', 1, 2, '1550101160043', 2, 1, 1, 1, 3, 3, 1, 3);

INSERT INTO LABORATORY (ID, NAME, FROM_ID, TO_ID, PROFESSOR_PNC, ROOM_ID, DAY_ID, SECTION_ID, GROUP_ID, SUBGROUP_ID, YEAR_ID, SEMESTER_ID, WEEKLY_OCCURRENCE_ID)
VALUES (3, 'O.O.P.', 5, 6, '1550102160043', 2, 1, 1, 1, 3, 3, 1, 3);

INSERT INTO LABORATORY (ID, NAME, FROM_ID, TO_ID, PROFESSOR_PNC, ROOM_ID, DAY_ID, SECTION_ID, GROUP_ID, SUBGROUP_ID, YEAR_ID, SEMESTER_ID, WEEKLY_OCCURRENCE_ID)
VALUES (4, 'P.T.', 4, 5, '1550102160043', 2, 1, 1, 1, 2, 3, 1, 3);

INSERT INTO STUDENT_LABORATORY (LABORATORY_ID, STUDENT_PNC)
VALUES (1, '1940826160041');
INSERT INTO STUDENT_LABORATORY (LABORATORY_ID, STUDENT_PNC)
VALUES (2, '1940826160041');
INSERT INTO STUDENT_LABORATORY (LABORATORY_ID, STUDENT_PNC)
VALUES (3, '1940826160041');
