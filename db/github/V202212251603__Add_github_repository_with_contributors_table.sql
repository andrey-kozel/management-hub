CREATE TABLE IF NOT EXISTS github_repository_with_contributors
(
    repository_id INT NOT NULL,
    contributor_id INT NOT NULL,
    FOREIGN KEY (contributor_id)  REFERENCES github_contributors (id),
    FOREIGN KEY (repository_id) REFERENCES github_repository (id)
);
