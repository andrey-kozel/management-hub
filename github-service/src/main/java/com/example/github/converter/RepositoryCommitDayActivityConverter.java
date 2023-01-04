package com.example.github.converter;

import com.example.github.dto.RepositoryCommitDayActivityDto;
import com.example.github.model.RepositoryCommitDayActivity;
import org.mapstruct.Mapper;

@Mapper
public interface RepositoryCommitDayActivityConverter {

    RepositoryCommitDayActivityDto toDto(final RepositoryCommitDayActivity repositoryCommitDayActivity);
}
