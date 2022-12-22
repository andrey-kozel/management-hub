package com.example.github.service;

import com.example.github.model.Repo;
import com.example.github.repository.GithubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {
    private final GithubRepository githubRepository;

   public List<Repo> findAllRepositoryByToken(String token) {
        return githubRepository.findAllRepositoryByToken(token);
    }
}
