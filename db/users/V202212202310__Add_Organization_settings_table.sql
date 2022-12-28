create table organization_settings
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    organization_id BIGSERIAL NOT NULL,
    access_token      TEXT      NOT NULL,
    UNIQUE (id, organization_id)
);

alter table users
    alter column name drop not null;