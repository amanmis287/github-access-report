package com.assignment.github_access_report.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

@Service
public class GithubService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${github.token}")
    private String token;

    public Map<String, List<String>> generateAccessReport(String org) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String reposUrl = "https://api.github.com/orgs/" + org + "/repos";

        ResponseEntity<List> reposResponse = restTemplate.exchange(
                reposUrl,
                HttpMethod.GET,
                entity,
                List.class
        );

        List<Map<String, Object>> repos = reposResponse.getBody();

        Map<String, List<String>> userAccessMap = new HashMap<>();

        if (repos == null) return new HashMap<>();

        for (Map<String, Object> repo : repos) {

            String repoName = repo.get("name").toString();

            String collaboratorsUrl =
                    "https://api.github.com/repos/" + org + "/" + repoName + "/contributors";

            ResponseEntity<List> collabResponse = restTemplate.exchange(
                    collaboratorsUrl,
                    HttpMethod.GET,
                    entity,
                    List.class
            );

            List<Map<String, Object>> collaborators = collabResponse.getBody();

            if (collaborators == null) {
                continue;
            }

            for (Map<String, Object> user : collaborators) {

                String username = user.get("login").toString();

                userAccessMap
                        .computeIfAbsent(username, k -> new ArrayList<>())
                        .add(repoName);
            }
        }

        return userAccessMap;
    }
}