INSERT INTO theatre(name, rows, seats_per_row) VALUES
                                                   ('Large Theatre', 25, 16),
                                                   ('Small Theatre', 20, 12);

INSERT INTO movie(title, duration, age_limit) VALUES
                                                   ('The Damnation', 111, 18),
                                                   ('2 Nights at Teddy''s', 78, null),
                                                   ('Scream 8', 101, 16),
                                                   ('The Mandalorian & Grogu', 163, null);

INSERT INTO category(name) VALUES
                               ('Comedy'),
                               ('Action'),
                               ('Horror'),
                               ('Thriller'),
                               ('Adventure'),
                               ('Kids'),
                               ('Family');

INSERT INTO movie_category(movie_id, category_id) VALUES
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

INSERT INTO showing(movie_id, theatre_id, start_time) VALUES
                                                          (1, 2, '2026-03-05 21:45'),
                                                          (2, 2, '2026-03-06 14:10'),
                                                          (3, 1, '2026-03-05 21:00'),
                                                          (4, 1, '2026-03-06 21:00');

INSERT INTO customer(name, phone) VALUES
                                      ('Donald Trump', '+1 202 456-1111'),
                                      ('Mette Frederiksen', '+45 19101977'),
                                      ('Lars Kragh Andersen', '+45 1337903'),
                                      ('Bente M', '+45 22301928'),
                                      ('Lars Ulykke', '+45 66666666'),
                                      ('Inger Roberg', '+45 10102112'),
                                      ('Pynte Sigurdsson', '+45 28934012'),
                                      ('Mogens G', '+45 19262008'),
                                      ('Jeffrey Kennedy', '+92 1005 50002983');

INSERT INTO reservation(customer_id, showing_id, reservation_time, status) VALUES
                                                                               (1, 3, null, 'CANCELLED'),
                                                                               (2, 2, null, 'CONFIRMED'),
                                                                               (3, 1, null, 'CANCELLED'),
                                                                               (4, 4, null, 'CONFIRMED'),
                                                                               (5, 1, null, 'CONFIRMED'),
                                                                               (6, 3, null, 'CONFIRMED'),
                                                                               (7, 4, null, 'CONFIRMED'),
                                                                               (8, 4, null, 'CONFIRMED'),
                                                                               (9, 4, null, 'CONFIRMED');

INSERT INTO employee(role, name) VALUES
                           ('SALES', 'Frodo Andersen'),
                           ('SALES', 'Mo Galko'),
                           ('INSPECTOR', 'Heidi Blåstrup'),
                           ('OPERATOR', 'Dan Mark');