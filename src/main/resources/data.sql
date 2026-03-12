INSERT IGNORE INTO theatre(id, name, row_count, seats_per_row)
VALUES (1, 'Theatre 1', 10, 10),  -- 10 rows × 10 seats = 100 seats
       (2, 'Theatre 2', 10, 15);  -- 10 rows × 15 seats = 150 seats

INSERT IGNORE INTO movie(title, duration, age_limit) VALUES
                                                   ('The Damnation', 111, 18),
                                                   ('2 Nights at Teddy''s', 78, NULL),
                                                   ('Scream 8', 101, 16),
                                                   ('The Mandalorian & Grogu', 163, NULL);

INSERT IGNORE INTO category(name) VALUES
                               ('Comedy'),
                               ('Action'),
                               ('Horror'),
                               ('Thriller'),
                               ('Adventure'),
                               ('Kids'),
                               ('Family');

INSERT IGNORE INTO movie_category(movie_id, category_id) VALUES
                                                      (1, 2),
                                                      (1, 3),
                                                      (1, 4),
                                                      (2, 6),
                                                      (2, 7),
                                                      (3, 1),
                                                      (3, 3),
                                                      (4, 2),
                                                      (4, 5),
                                                      (4, 6),
                                                      (4, 7);

INSERT IGNORE INTO showing(movie_id, theatre_id, start_time) VALUES
                                                          (1, 2, '2026-03-05 21:45'),
                                                          (2, 2, '2026-03-06 14:10'),
                                                          (3, 1, '2026-03-05 21:00'),
                                                          (4, 1, '2026-03-06 21:00');

INSERT IGNORE INTO customer(name, phone, age) VALUES
    ('Donald Trump', '+1 202 456-1111', 78),
    ('Mette Frederiksen', '+45 19101977', 47),
    ('Lars Kragh Andersen', '+45 1337903', 35),
    ('Bente M', '+45 22301928', 55),
    ('Lars Ulykke', '+45 66666666', 42),
    ('Inger Roberg', '+45 10102112', 51),
    ('Pynte Sigurdsson', '+45 28934012', 29),
    ('Mogens G', '+45 19262008', 61),
    ('Jeffrey Kennedy', '+92 1005 50002983', 45);

INSERT IGNORE INTO reservation(customer_id, showing_id, reservation_time, status) VALUES
                                                                               (1, 3, NULL, 'CANCELLED'),
                                                                               (2, 2, NULL, 'CONFIRMED'),
                                                                               (3, 1, NULL, 'CANCELLED'),
                                                                               (4, 4, NULL, 'CONFIRMED'),
                                                                               (5, 1, NULL, 'CONFIRMED'),
                                                                               (6, 3, NULL, 'CONFIRMED'),
                                                                               (7, 4, NULL, 'CONFIRMED'),
                                                                               (8, 4, NULL, 'CONFIRMED'),
                                                                               (9, 4, NULL, 'CONFIRMED');

INSERT IGNORE INTO employee(role, name) VALUES
                           ('SALES', 'Frodo Andersen'),
                           ('SALES', 'Mo Galko'),
                           ('INSPECTOR', 'Heidi Blåstrup'),
                           ('OPERATOR', 'Dan Mark');