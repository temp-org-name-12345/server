CREATE DATABASE ddakdae;

\c ddakdae;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
    id           SERIAL   NOT NULL,
    email        varchar  NOT NULL,
    nickname     varchar  NOT NULL,
    profile_url   varchar  NOT NULL,
    created_by    varchar,
    created_at    TIMESTAMP,
    refresh_token varchar,

    UNIQUE(email),
    Unique(nickname),
    CONSTRAINT users_pk PRIMARY KEY(id)
);