package com.adrrriannn.issue.manager.service;

import com.adrrriannn.issue.manager.dto.IssueDto;

import java.util.List;

/**
 * Created by adrian on 17/06/19.
 */
public interface IssueService {
    List<IssueDto> getAllIssues(String user, String repo);
    IssueDto getIssue(String user, String repo, String id);
}
