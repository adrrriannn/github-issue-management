package com.adrrriannn.issue.manager.controller;

import com.adrrriannn.issue.manager.dto.IssueDto;
import com.adrrriannn.issue.manager.service.IssueService;

import java.util.List;

/**
 * Created by adrian on 17/06/19.
 */
public class IssueController {

    private IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }


    public IssueDto getIssue(String id) {
        return issueService.getIssue(id);
    }

    public List<IssueDto> getAllIssues() {
        return issueService.getAllIssues();
    }

    public IssueDto createIssue(IssueDto issueDto) {
        return issueService.createIssue(issueDto);
    }

    public void deleteIssue(String id) {
        issueService.deleteIssue(id);
    }
}
