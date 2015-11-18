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

INSERT INTO PERSON (PNC, FIRST_NAME, LAST_NAME, EMAIL)
VALUES ('0000000000000', 'admin', 'admin', 'admin@software.ucv.ro');
INSERT INTO user (USERNAME, PASSWORD, STATE_ID, TYPE_ID)
VALUES ('0000000000000', '$2a$10$csrGgdaao3nEiNuYDXm7qO0BHgnP2eUJeJFOHZQT9M0QEwqQupo8i', 1, 3);
