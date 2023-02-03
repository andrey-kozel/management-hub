package com.example.github.converter;

import com.example.github.dto.Permissions;
import com.example.github.dto.RepoDto;
import com.example.github.dto.SaveRepositoryDto;
import com.example.github.model.GitRepository;
import com.example.github.model.Repo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.Date;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RepoConverter {
    List<RepoDto> toDto(List<Repo> listRepo);

    GitRepository fromDto(final SaveRepositoryDto repositoryDto, final Long organizationId,
                          final Boolean isSynchronizationEnabled, final Date synchronizedAt);

    default String toString(Permissions permissions) throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(permissions);
    }
}
