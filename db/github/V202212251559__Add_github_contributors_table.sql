CREATE TABLE IF NOT EXISTS github_contributors
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    login TEXT NOT NULL ,
    account_id INT NOT NULL ,
    repository_name TEXT NOT NULL
);
