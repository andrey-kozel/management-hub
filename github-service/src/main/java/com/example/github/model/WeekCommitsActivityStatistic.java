package com.example.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class WeekCommitsActivityStatistic {
    @JsonProperty("week")
    Long timestamp;

    List<Long> days;

    @JsonProperty("total")
    Long totalCommits;
}
