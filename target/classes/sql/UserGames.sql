CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    nickname VARCHAR(255) NOT NULL,
    birthday DATE,
    password VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2)
);


CREATE TABLE Games (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    release_date DATE,
    rating DECIMAL(3, 1),
    cost DECIMAL(10, 2) NOT NULL,
    description TEXT
);


CREATE TABLE UserPurchases (
    user_id INTEGER REFERENCES Users(id),
    game_id INTEGER REFERENCES Games(id),
    PRIMARY KEY (user_id, game_id)
);