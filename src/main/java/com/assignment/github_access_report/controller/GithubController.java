package com.assignment.github_access_report.controller;

import com.assignment.github_access_report.service.GithubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/github-access-report/{org}")
    public Map<String, List<String>> getAccessReport(@PathVariable String org) {

        return githubService.generateAccessReport(org);
    }
}