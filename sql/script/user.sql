INSERT INTO hyperTest.role (id, type) VALUES (1, 'ADMIN');
INSERT INTO hyperTest.role (id, type) VALUES (2, 'USER');

INSERT INTO hyperTest.user (id, display_status, email, first_name, last_name, password, username) VALUES (1, 'ACTIVE', 'admin@gmail.com', 'admin', 'shaheb', 'admin', 'admin');
INSERT INTO hyperTest.user_role (user_id, role_id) VALUES (1, 1);