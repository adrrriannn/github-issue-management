package com.adrrriannn.issue.manager.service;

import com.adrrriannn.issue.manager.dto.IssueDto;
import com.adrrriannn.issue.manager.gateway.IssueGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

/**
 * Created by adrian on 17/06/19.
 */
@RunWith(MockitoJUnitRunner.class)
public class IssueServiceImplTest {

    @Mock
    private IssueGateway issueGateway;

    @InjectMocks
    private IssueServiceImpl issueService;

    private static final IssueDto ISSUE_DTO = IssueDto.builder().id("someId").build();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn(Arrays.asList(ISSUE_DTO)).when(issueGateway).listAllIssues();
        doReturn(ISSUE_DTO).when(issueGateway).getIssue(anyString());
        doNothing().when(issueGateway).deleteIssue(anyString());
        doAnswer(returnsFirstArg()).when(issueGateway).createIssue(any());
    }

    @Test
    public void listAllIssues() {
        issueService.getAllIssues();
        Mockito.verify(issueGateway).listAllIssues();
    }

    @Test
    public void getIssue() {
        IssueDto issueDto = issueService.getIssue("id");
        Mockito.verify(issueGateway).getIssue("id");
    }

    @Test
    public void createIssue() {
        IssueDto createdIssue = issueService.createIssue(ISSUE_DTO);
        Mockito.verify(issueGateway).createIssue(ISSUE_DTO);
    }

    @Test
    public void deleteIssue() {
        issueService.deleteIssue("id");
        Mockito.verify(issueGateway).deleteIssue("id");
    }

}
