ALTER TABLE github_repository_with_contributors
    ADD CONSTRAINT uniqueConnection UNIQUE (repository_id, contributor_id)
