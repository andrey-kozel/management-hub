package com.example.github.dto;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class RepositoryCommitDayActivityDto {
    Long date;
    Long commits;
}
