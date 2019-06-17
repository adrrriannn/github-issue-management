package com.adrrriannn.issue.manager.gateway;

import com.adrrriannn.issue.manager.dto.GitHubIssueDto;
import com.adrrriannn.issue.manager.dto.IssueDto;
import com.adrrriannn.issue.manager.mapper.GitHubIssueMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adrian on 17/06/19.
 */
public class GitHubIssueGateway implements IssueGateway{

    private RestTemplate restTemplate;
    private GitHubIssueMapper gitHubIssueMapper;

    private String gitHubIssueApiUrl;

    public GitHubIssueGateway(RestTemplate restTemplate,
                              GitHubIssueMapper gitHubIssueMapper,
                              String gitHubIssueApiUrl) {
        this.restTemplate = restTemplate;
        this.gitHubIssueMapper = gitHubIssueMapper;
        this.gitHubIssueApiUrl = gitHubIssueApiUrl;
    }

    @Override
    public List<IssueDto> listAllIssues() {
        ResponseEntity<List<GitHubIssueDto>> responseEntity =
            restTemplate.exchange(gitHubIssueApiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<GitHubIssueDto>>(){});
        
        return responseEntity.getBody().stream()
                .map(gitHubIssueMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public IssueDto getIssue(String id) {
        ResponseEntity<GitHubIssueDto> responseEntity =
                restTemplate.getForEntity(gitHubIssueApiUrl + id, GitHubIssueDto.class);
        
        return gitHubIssueMapper.map(responseEntity.getBody()); 
    }

    @Override
    public IssueDto createIssue(IssueDto issueDto) {
        
        GitHubIssueDto gitHubIssueDto = gitHubIssueMapper.map(issueDto);
        
        ResponseEntity<GitHubIssueDto> responseEntity =
                restTemplate.postForEntity(gitHubIssueApiUrl, gitHubIssueDto, GitHubIssueDto.class);

        return gitHubIssueMapper.map(responseEntity.getBody());
    }

    @Override
    public void deleteIssue(String id) {
        restTemplate.delete(gitHubIssueApiUrl + id);
    }
}
