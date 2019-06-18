package com.adrrriannn.issue.manager.service;

import com.adrrriannn.issue.manager.dto.IssueDto;
import com.adrrriannn.issue.manager.gateway.IssueGateway;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by adrian on 17/06/19.
 */
@Service
public class IssueServiceImpl implements IssueService {

    private IssueGateway issueGateway;

    public IssueServiceImpl(IssueGateway issueGateway) {
        this.issueGateway = issueGateway;
    }

    @Override
    public List<IssueDto> getAllIssues() {
        return issueGateway.listAllIssues();
    }

    @Override
    public void deleteIssue(String id) {
        issueGateway.deleteIssue(id);
    }

    @Override
    public IssueDto createIssue(IssueDto issueDto) {
        return issueGateway.createIssue(issueDto);
    }

    @Override
    public IssueDto getIssue(String id) {
        return issueGateway.getIssue(id);
    }
}
