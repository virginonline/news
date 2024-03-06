CREATE TABLE IF NOT EXISTS type
(
    id    SERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL UNIQUE,
    color VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS news
(
    id          SERIAL PRIMARY KEY NOT NULL,
    title       VARCHAR(255),
    summary     VARCHAR(255),
    description TEXT,
    type_id     INT,
    CONSTRAINT type_fk FOREIGN KEY (type_id) REFERENCES type (id)
);
