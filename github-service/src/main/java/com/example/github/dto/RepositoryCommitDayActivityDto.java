package com.example.github.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RepositoryCommitDayActivityDto {
    Long date;
    Long commits;
}
