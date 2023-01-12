package com.example.github.converter;

import com.example.github.dto.GitHubRepositoryDto;
import com.example.github.model.GitHubRepository;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GitHubRepositoryConverter {

  GitHubRepository fromDto(final GitHubRepositoryDto gitHubRepositoryDto);

  GitHubRepositoryDto toDto(final GitHubRepository gitHubRepository);

  List<GitHubRepositoryDto> modelsToDto(final List<GitHubRepository> repositories);

  List<GitHubRepository> dtoToModels(final List<GitHubRepositoryDto> repositories);

}
