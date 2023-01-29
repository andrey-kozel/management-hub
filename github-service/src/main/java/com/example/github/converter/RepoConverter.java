package com.example.github.converter;

import com.example.github.dto.RepoDto;
import com.example.github.model.Repo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RepoConverter {
    List<RepoDto> toDto(List<Repo> listRepo);
}
