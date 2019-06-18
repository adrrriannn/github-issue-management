package com.adrrriannn.issue.manager.gateway;

import com.adrrriannn.issue.manager.dto.GitHubIssueDto;
import com.adrrriannn.issue.manager.dto.IssueDto;
import com.adrrriannn.issue.manager.mapper.GitHubIssueMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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
    private static final GitHubIssueDto GIT_HUB_ISSUE_DTO = GitHubIssueDto.builder().number("someId").build();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gitHubIssueGateway = new GitHubIssueGateway(restTemplate, issueMapper, gitHubIssuesUrl);

        doReturn(ISSUE_DTO).when(issueMapper).map(GIT_HUB_ISSUE_DTO);
        doReturn(GIT_HUB_ISSUE_DTO).when(issueMapper).map(ISSUE_DTO);

        doReturn(ResponseEntity.ok(Arrays.asList(GIT_HUB_ISSUE_DTO))).when(restTemplate).exchange(anyString(),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<GitHubIssueDto>>(){}));
        doReturn(ResponseEntity.ok(GIT_HUB_ISSUE_DTO)).when(restTemplate).getForEntity(anyString(), any(Class.class));
        doReturn(ResponseEntity.ok(GIT_HUB_ISSUE_DTO)).when(restTemplate).postForEntity(anyString(), any(GitHubIssueDto.class), any(Class.class));
        doNothing().when(restTemplate).delete(gitHubIssuesUrl);
    }


    @Test
    public void listAllIssues() throws Exception {
        gitHubIssueGateway.listAllIssues("user", "repo");
        verify(restTemplate).exchange(anyString(),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<GitHubIssueDto>>(){}));
    }

    @Test
    public void getIssue() throws Exception {
        gitHubIssueGateway.getIssue("user", "repo", "someId");
        verify(restTemplate).getForEntity(anyString(), any(Class.class));
    }

}
