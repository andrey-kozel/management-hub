package com.example.github.converter;

import com.example.github.dto.RepoDto;
import com.example.github.model.Repo;

import java.util.List;

public interface RepoConverter {
    List<RepoDto> toDto(List<Repo> listRepo);
}
