package com.adrrriannn.issue.manager.controller;

import com.adrrriannn.issue.manager.dto.IssueDto;
import com.adrrriannn.issue.manager.service.IssueService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
public class IssueControllerTest {

    @Mock
    private IssueService issueService;

    @InjectMocks
    private IssueController issueController;

    private static final IssueDto ISSUE_DTO = IssueDto.builder().id("someId").build();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn(Arrays.asList(ISSUE_DTO)).when(issueService).getAllIssues();
        doReturn(ISSUE_DTO).when(issueService).getIssue(anyString());
        doNothing().when(issueService).deleteIssue(anyString());
        doAnswer(returnsFirstArg()).when(issueService).createIssue(any());
    }

    @Test
    public void listAllIssues() {
        issueController.getAllIssues();
        Mockito.verify(issueService).getAllIssues();
    }

    @Test
    public void getIssue() {
        IssueDto issueDto = issueController.getIssue("id");
        Mockito.verify(issueService).getIssue("id");
    }

    @Test
    public void createIssue() {
        IssueDto createdIssue = issueController.createIssue(ISSUE_DTO);
        Mockito.verify(issueService).createIssue(ISSUE_DTO);
    }

    @Test
    public void deleteIssue() {
        issueController.deleteIssue("id");
        Mockito.verify(issueService).deleteIssue("id");
    }
}
