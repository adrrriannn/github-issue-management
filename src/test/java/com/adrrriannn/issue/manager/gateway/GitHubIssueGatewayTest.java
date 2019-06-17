package com.adrrriannn.issue.manager.gateway;

import com.adrrriannn.issue.manager.dto.GitHubIssueDto;
import com.adrrriannn.issue.manager.dto.IssueDto;
import com.adrrriannn.issue.manager.mapper.GitHubIssueMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by adrian on 17/06/19.
 */
public class GitHubIssueGatewayTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private GitHubIssueMapper issueMapper;

    private String gitHubIssuesUrl = "SomeUrl";
    private GitHubIssueGateway gitHubIssueGateway;

    private static final IssueDto ISSUE_DTO = IssueDto.builder().id("someId").build();
    private static final GitHubIssueDto GIT_HUB_ISSUE_DTO = GitHubIssueDto.builder().id("someId").build();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gitHubIssueGateway = new GitHubIssueGateway(restTemplate, issueMapper, gitHubIssuesUrl);
        doReturn(ResponseEntity.ok(Arrays.asList(GIT_HUB_ISSUE_DTO))).when(restTemplate).exchange(anyString(),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<GitHubIssueDto>>(){}));
        doReturn(ResponseEntity.ok(GIT_HUB_ISSUE_DTO)).when(restTemplate).getForEntity(gitHubIssuesUrl, GitHubIssueDto.class);
        doReturn(ResponseEntity.ok(GIT_HUB_ISSUE_DTO)).when(restTemplate).postForEntity(gitHubIssuesUrl, GIT_HUB_ISSUE_DTO, GitHubIssueDto.class);
        doNothing().when(restTemplate).delete(gitHubIssuesUrl);
    }


    @Test
    public void listAllIssues() throws Exception {
        gitHubIssueGateway.listAllIssues();
        verify(restTemplate).exchange(anyString(),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<GitHubIssueDto>>(){}));
    }

    @Test
    public void getIssue() throws Exception {
        gitHubIssueGateway.getIssue("someId");
        verify(restTemplate).exchange(anyString(), any(HttpMethod.class), null, GitHubIssueDto.class, any(Map.class));
    }

    @Test
    public void createIssue() throws Exception {
        gitHubIssueGateway.createIssue(ISSUE_DTO);
        verify(issueMapper).map(ISSUE_DTO);
        verify(restTemplate).postForEntity(anyString(), any(GitHubIssueDto.class), GitHubIssueDto.class, new HashMap<>());
    }

    @Test
    public void deleteIssue() throws Exception {
        gitHubIssueGateway.deleteIssue("someId");
        verify(restTemplate).delete(anyString());
    }

}
