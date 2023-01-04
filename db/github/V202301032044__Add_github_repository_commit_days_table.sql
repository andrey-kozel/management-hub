CREATE TABLE github_repository_commit_day_activity_table
(
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    date BIGINT NOT NULL,
    commits BIGINT NOT NULL,
    repository_id BIGINT NOT NULL,
    FOREIGN KEY (repository_id) REFERENCES github_repository (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT uniqueConnection UNIQUE (date, repository_id)
)
