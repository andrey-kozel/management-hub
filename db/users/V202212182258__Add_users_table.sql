CREATE TABLE users
(
    id         BIGSERIAL NOT NULL PRIMARY KEY,
    name       TEXT      NOT NULL,
    account_id TEXT      NOT NULL,
    provider   TEXT      NOT NULL,

    UNIQUE (account_id, provider)
);
