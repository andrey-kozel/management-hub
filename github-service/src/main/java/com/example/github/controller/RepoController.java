package com.example.github.controller;

import com.example.github.converter.RepoConverter;
import com.example.github.dto.RepoDto;
import com.example.github.model.Repo;
import com.example.github.service.GithubService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class RepoController {
    private final GithubService githubService;
    private final RepoConverter repoConverter;

    @GetMapping("/repositories")
    protected List<RepoDto> repositories(HttpServletRequest request, HttpServletResponse response,
                                         Model model, @ModelAttribute("token")String token) {
        response.setContentType(APPLICATION_JSON_VALUE);
        List<Repo> listRepo = githubService.findAllRepositoryByToken(token);
        model.addAttribute("listRepo", listRepo);
        return repoConverter.toDto(listRepo);
    }
}
