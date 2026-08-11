INSERT INTO car (make, model, year, power_hp, drivetrain, category)
VALUES
    ('BMW',          'M3 Competition G80',      2022, 510, 'RWD', 'Sedan'),
    ('BMW',          'M4 CSL G82',               2023, 550, 'RWD', 'Coupe'),
    ('BMW',          'M5 CS F90',                2022, 627, 'AWD', 'Sedan'),
    ('BMW',          'M2 G87',                   2023, 460, 'RWD', 'Coupe'),
    ('Porsche',      '911 GT3 992',              2023, 510, 'RWD', 'Coupe'),
    ('Porsche',      '718 Cayman GT4 RS',        2022, 493, 'RWD', 'Coupe'),
    ('Porsche',      'Panamera Turbo S',         2023, 630, 'AWD', 'Sedan'),
    ('Mercedes-AMG', 'C63 S E Performance',      2023, 671, 'AWD', 'Sedan'),
    ('Mercedes-AMG', 'GT 63 S 4-Door',           2022, 630, 'AWD', 'Coupe'),
    ('Mercedes-AMG', 'A45 S',                    2022, 421, 'AWD', 'Hatchback');

INSERT INTO track (name, country, length_km, configuration)
VALUES
    ('Nurburgring GP',    'Germany', 5.148, 'GP Circuit'),
    ('Spa-Francorchamps', 'Belgium', 7.004, 'Full Circuit'),
    ('Hockenheimring',    'Germany', 4.574, 'GP Circuit'),
    ('Mugello Circuit',   'Italy',   5.245, 'Full Circuit'),
    ('Red Bull Ring',     'Austria', 4.318, 'Grand Prix Circuit');

INSERT INTO session (car_id, track_id, session_date, conditions, notes)
VALUES
    (1, 1, '2024-09-15', 'Dry', 'First time on Nurburgring GP'),
    (5, 1, '2024-09-15', 'Dry', 'Setup run before qualifying'),
    (1, 2, '2024-10-03', 'Wet', 'Tricky conditions, careful laps'),
    (6, 3, '2024-11-10', 'Dry', 'New tires, personal best attempt'),
    (8, 4, '2025-02-20', 'Dry', 'Track day with friends'),
    (9, 5, '2025-03-05', 'Dry', 'Testing new brake pads');

INSERT INTO lap (session_id, lap_number, lap_time_ms,
                 sector1_ms, sector2_ms, sector3_ms, is_valid)
VALUES
    (1, 1, 112441, 31200, 45800, 35441, true),
    (1, 2, 110983, 30900, 44800, 35283, true),
    (1, 3, 111250, 31100, 45000, 35150, true),

    (2, 1, 105330, 29100, 42800, 33430, true),
    (2, 2, 104110, 28800, 42000, 33310, true),

    (3, 1, 165220, 50000, 65000, 50220, true),
    (3, 2, 172400, 52000, 68000, 52400, false),

    (4, 1, 98450,  27200, 38900, 32350, true),
    (4, 2, 97800,  27000, 38600, 32200, true),

    (5, 1, 128300, 35500, 52000, 40800, true),
    (5, 2, 130150, 36200, 52800, 41150, true),

    (6, 1, 96200,  26800, 37600, 31800, true),
    (6, 2, 95800,  26600, 37400, 31800, true);