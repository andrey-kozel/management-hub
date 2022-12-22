package com.example.github.controller;

import com.example.github.model.Repo;
import com.example.github.service.GithubService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.List;

@Validated
@Controller
@RequiredArgsConstructor
public class RepoController {
    private final GithubService githubService;

    @GetMapping("/repository")
    protected String findAllRepository(Model model, @ModelAttribute("token")String token) {
        List<Repo> listRepo = githubService.findAllRepositoryByToken(token);
        model.addAttribute("listRepo", listRepo);
        return "listRepo";
    }
}
