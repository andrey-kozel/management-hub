package com.example.github.converter;

import com.example.github.dto.Permissions;
import com.example.github.dto.SaveRepositoryDto;
import com.example.github.model.GitRepository;
import com.google.gson.Gson;
import org.mapstruct.Mapper;

@Mapper
public interface GithubRepositoryConverter {

    GitRepository fromDto(final SaveRepositoryDto repositoryDto);

    default String toString(Permissions permissions) {
        Gson gson = new Gson();
        return gson.toJson(permissions);
    }
}
