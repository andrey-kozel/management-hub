package com.example.github.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Jacksonized
@Builder
@Value
@Table("github_repository_commit_day_activity_table")
public class RepositoryCommitDayActivity {
    Long date;
    Long commits;

    @Column("repository_id")
    Long repositoryId;
}
