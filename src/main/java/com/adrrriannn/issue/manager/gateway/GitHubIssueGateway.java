package com.adrrriannn.issue.manager.gateway;

import com.adrrriannn.issue.manager.dto.GitHubIssueDto;
import com.adrrriannn.issue.manager.dto.IssueDto;
import com.adrrriannn.issue.manager.mapper.GitHubIssueMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by adrian on 17/06/19.
 */
@Component
public class GitHubIssueGateway implements IssueGateway {

    private RestTemplate restTemplate;
    private GitHubIssueMapper gitHubIssueMapper;

    private String gitHubIssueApiUrl;

    public GitHubIssueGateway(RestTemplate restTemplate,
                              GitHubIssueMapper gitHubIssueMapper,
                              @Value("${issues.api.url}") String gitHubIssueApiUrl) {
        this.restTemplate = restTemplate;
        this.gitHubIssueMapper = gitHubIssueMapper;
        this.gitHubIssueApiUrl = gitHubIssueApiUrl;
    }

    @Override
    public List<IssueDto> listAllIssues(String user, String repo) {

        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("user", user);
        uriParams.put("repo", repo);
        String url = UriComponentsBuilder.fromUriString(gitHubIssueApiUrl).build(uriParams).toString();
        ResponseEntity<List<GitHubIssueDto>> responseEntity =
            restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<GitHubIssueDto>>(){});
        
        return responseEntity.getBody().stream()
                .map(gitHubIssueMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public IssueDto getIssue(String user, String repo, String id) {
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("user", user);
        uriParams.put("repo", repo);
        String url = UriComponentsBuilder.fromUriString(gitHubIssueApiUrl).build(uriParams).toString();

        ResponseEntity<GitHubIssueDto> responseEntity =
                restTemplate.getForEntity(url + id, GitHubIssueDto.class);
        
        return gitHubIssueMapper.map(responseEntity.getBody()); 
    }
}
