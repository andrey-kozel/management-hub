CREATE TABLE IF NOT EXISTS github_repository
(
    id                         BIGSERIAL NOT NULL UNIQUE,
    github_id                  BIGINT    NOT NULL,
    node_id                    TEXT,
    name                       TEXT,
    full_name                  TEXT,
    is_private                 BOOLEAN,
    description                TEXT,
    is_fork                    BOOLEAN,
    created_at                 TIMESTAMP,
    updated_at                 TIMESTAMP,
    pushed_at                  TIMESTAMP,
    home_page                  TEXT,
    size                       BIGINT,
    stargazers_count           INT,
    watchers_count             INT,
    forks_count                INT,
    open_issues_count          INT,
    license                    TEXT,
    default_branch             TEXT,
    language                   TEXT,
    permissions                JSONB,
    is_archived                BOOLEAN,
    is_disabled                BOOLEAN,

    is_synchronization_enabled BOOLEAN   NOT NULL DEFAULT FALSE,
    synchronized_at            TIMESTAMP NOT NULL,
    organization_id            BIGINT      NOT NULL,

    PRIMARY KEY (id)
);
