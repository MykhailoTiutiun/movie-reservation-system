DELETE
FROM seats;
DELETE
FROM showtimes;
DELETE
FROM auditoriums;
DELETE
FROM movies;
DELETE
FROM app_users;

INSERT INTO app_users(id, username, password, role)
VALUES (10, 'Test', 'Test', 'USER');
INSERT INTO movies(id, title, description)
VALUES (10, 'Test', 'Test');
INSERT INTO movies(id, title, description)
VALUES (12, 'updateTest', 'updateTest');
INSERT INTO movies(id, title, description)
VALUES (13, 'deleteTest', 'deleteTest');
INSERT INTO auditoriums(id, name, description, movie_id)
VALUES (10, 'Test', 'Test', 10);
INSERT INTO auditoriums(id, name, description, movie_id)
VALUES (12, 'MovieIdNullTest', 'MovieIdNullTest', NULL);
INSERT INTO auditoriums(id, name, description, movie_id)
VALUES (13, 'deleteTest', 'deleteTest', 10);
INSERT INTO showtimes(id, date, start_time, end_time, auditorium_id)
VALUES (10, '2024-08-31', '10:00', '12:00', 10);
INSERT INTO showtimes(id, date, start_time, end_time, auditorium_id)
VALUES (12, '2024-08-31', '16:00', '18:00', 10);
INSERT INTO showtimes(id, date, start_time, end_time, auditorium_id)
VALUES (13, '2024-08-31', '8:00', '10:00', 10);
INSERT INTO seats(id, name, availability, auditorium_id, showtime_id, user_id)
VALUES (10, 'Test', true, 10, 10, 10);
INSERT INTO seats(id, name, availability, auditorium_id, showtime_id, user_id)
VALUES (12, 'reserveTest', true, 10, 10, null);