INSERT INTO drivers(first_name, last_name, phone)
VALUES ('TEST', 'DRIVER1', '+40711111111');
INSERT INTO drivers(first_name, last_name, phone)
VALUES ('TEST', 'DRIVER2', '+40722222222');

INSERT INTO cars(name, color, registration_number, register_time)
VALUES ('DACIA LOGAN', 'ALB', 'B-01-AAA', now());
INSERT INTO cars(name, color, registration_number, register_time)
VALUES ('DACIA DUSTER', 'NEGRU', 'B-02-BBB', now());

INSERT INTO trips(start_location, end_location, driver_id)
VALUES (1232.24, 1242.35, 1);

INSERT INTO trips(start_location, end_location)
VALUES (1231.24, 1241.35);

