CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50),
    created TIMESTAMP,
    last_login TIMESTAMP,
    token VARCHAR(255),
    is_active BOOLEAN
);

CREATE TABLE IF NOT EXISTS phones (
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    number VARCHAR(20),
    city_code SMALLINT,
    country_code VARCHAR(4)
);