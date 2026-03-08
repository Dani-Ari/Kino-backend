CREATE TABLE IF NOT EXISTS theatre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    rows INT NOT NULL,
    seats_per_row INT NOT NULL
);

CREATE TABLE IF NOT EXISTS seat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    theatre_id INT NOT NULL,
    row_number INT NOT NULL,
    seat_number INT NOT NULL,
    FOREIGN KEY (theatre_id) REFERENCES theatre(id),
    UNIQUE (theatre_id, row_number, seat_number)
);

CREATE TABLE IF NOT EXISTS movie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    duration INT,
    age_limit INT
);

CREATE TABLE IF NOT EXISTS category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS movie_category (
    movie_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (movie_id, category_id),
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS showing (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT NOT NULL,
    theatre_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (theatre_id) REFERENCES theatre(id)
);

CREATE TABLE IF NOT EXISTS customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    showing_id INT NOT NULL,
    reservation_time TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (showing_id) REFERENCES showing(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ticket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    showing_id INT NOT NULL,
    seat_id INT NOT NULL,
    reservation_id INT,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (showing_id) REFERENCES showing(id) ON DELETE CASCADE,
    FOREIGN KEY (seat_id) REFERENCES seat(id),
    FOREIGN KEY (reservation_id) REFERENCES reservation(id),
    UNIQUE (showing_id, seat_id)
);

CREATE TABLE IF NOT EXISTS employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);
