CREATE DATABASE ddakdae;

\c ddakdae;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
    id         SERIAL   NOT NULL,
    email      varchar  NOT NULL,
    nickname   varchar  NOT NULL,
    createdBy  varchar,
    createdAt  TIMESTAMP,
    UNIQUE(email, nickname),
    CONSTRAINT users_pk PRIMARY KEY(id)
);