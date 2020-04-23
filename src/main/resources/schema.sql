DROP TABLE IF EXISTS drivers CASCADE;
DROP TABLE IF EXISTS cars CASCADE;
DROP TABLE IF EXISTS car_driver_history CASCADE;
DROP TABLE IF EXISTS payments CASCADE;
DROP TABLE IF EXISTS trips CASCADE;

CREATE TABLE drivers
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name  VARCHAR(20) NOT NULL,
    phone      VARCHAR(15) NOT NULL UNIQUE
);

CREATE TABLE cars
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    driver_id           INT,
    name                VARCHAR(20) NOT NULL,
    color               VARCHAR(20) NOT NULL,
    registration_number VARCHAR(10) NOT NULL UNIQUE,
    register_time       TIMESTAMP   NOT NULL,
    FOREIGN KEY (driver_id) REFERENCES drivers (id)
);

CREATE TABLE car_driver_history
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    car_id           INT,
    driver_id        INT,
    start_alloc_time TIMESTAMP NOT NULL,
    end_alloc_time   TIMESTAMP,
    FOREIGN KEY (car_id) REFERENCES cars (id),
    FOREIGN KEY (driver_id) REFERENCES drivers (id)
);

CREATE TABLE trips
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    start_location DECIMAL(15, 10),
    end_location   DECIMAL(15, 10),
    rating         SMALLINT,
    driver_id      INT,
    FOREIGN KEY (driver_id) REFERENCES drivers (id)
);

CREATE TABLE payments
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    start   TIMESTAMP NOT NULL,
    end     TIMESTAMP,
    status  VARCHAR(10),
    price   DECIMAL(5, 2),
    reward  DECIMAL(5, 2),
    trip_id INT,
    FOREIGN KEY (trip_id) REFERENCES trips (id)
);
