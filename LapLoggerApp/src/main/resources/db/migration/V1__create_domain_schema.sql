CREATE TABLE IF NOT EXISTS car (
    id BIGSERIAL PRIMARY KEY,
    make VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    year INT NOT NULL,
    power_hp INT NOT NULL,
    drivetrain VARCHAR(3) NOT NULL CHECK (drivetrain IN ('AWD', 'RWD', 'FWD')),
    category VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS track (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    country VARCHAR(100) NOT NULL,
    length_km DECIMAL(6,3) NOT NULL,
    configuration VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS session (
    id BIGSERIAL PRIMARY KEY,
    car_id BIGINT NOT NULL REFERENCES car(id),
    track_id BIGINT NOT NULL REFERENCES track(id),
    session_date DATE NOT NULL,
    conditions VARCHAR(100),
    notes TEXT
);

CREATE TABLE IF NOT EXISTS lap (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT NOT NULL REFERENCES session(id) ON DELETE CASCADE,
    lap_number INT NOT NULL,
    lap_time_ms BIGINT NOT NULL,
    sector1_ms BIGINT,
    sector2_ms BIGINT,
    sector3_ms BIGINT,
    is_valid BOOLEAN NOT NULL DEFAULT TRUE
);