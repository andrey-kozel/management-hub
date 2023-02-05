ALTER TABLE github_repository
    ADD CONSTRAINT UNIQUE_GITHUB_ID UNIQUE (github_id);
