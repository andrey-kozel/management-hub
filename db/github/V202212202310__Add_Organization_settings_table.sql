create table organization_settings
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    organization_id BIGSERIAL NOT NULL,
    access_token      TEXT      NOT NULL,
    UNIQUE (id, organization_id)
);

alter table organization_settings drop constraint organization_settings_id_organization_id_key;
alter table organization_settings add constraint organization_id unique (organization_id)