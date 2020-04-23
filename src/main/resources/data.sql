INSERT INTO drivers(id, first_name, last_name, phone)
VALUES (101, 'TEST', 'DRIVER1', '+40711111111');
INSERT INTO drivers(id, first_name, last_name, phone)
VALUES (102, 'TEST', 'DRIVER2', '+40722222222');

INSERT INTO cars(id, name, color, registration_number, register_time, driver_id)
VALUES (100, 'DACIA LOGAN', 'ALB', 'B-01-AAA', now(), 101);
INSERT INTO cars(id, name, color, registration_number, register_time)
VALUES (101, 'DACIA DUSTER', 'NEGRU', 'B-02-BBB', now());

INSERT INTO trips(id, start_location, end_location, driver_id, rating)
VALUES (100, 1232.24, 1242.35, 101, 5);

INSERT INTO trips(id, start_location, end_location, driver_id, rating)
VALUES (101, 1231.24, 1241.35, 101, 4);

INSERT INTO trips(start_location, end_location)
VALUES (1231.24, 1241.35);

INSERT INTO payments(start, end, status, price, trip_id)
VALUES (TIMESTAMP '2020-04-21 10:33:40.000000', TIMESTAMP '2020-04-21 10:45:41.000000', 'SUCCEEDED', 123.45, 100);

INSERT INTO payments(start, end, status, price, trip_id)
VALUES (TIMESTAMP '2020-04-22 10:33:40.000000', TIMESTAMP '2020-04-22 10:45:41.000000', 'SUCCEEDED', 55.21, 101);

INSERT INTO car_driver_history(car_id, driver_id, start_alloc_time, end_alloc_time)
VALUES (100, 101, TIMESTAMP '2020-02-20 15:30:40.000000', TIMESTAMP '2020-04-22 18:30:40.000000');

INSERT INTO car_driver_history(car_id, driver_id, start_alloc_time)
VALUES (101, 101, TIMESTAMP '2020-04-22 18:30:41.000000');



