package com.adrrriannn.issue.manager.mapper;

import com.adrrriannn.issue.manager.dto.GitHubIssueDto;
import com.adrrriannn.issue.manager.dto.IssueDto;
import org.springframework.stereotype.Component;

/**
 * Created by adrian on 17/06/19.
 */
@Component
public class GitHubIssueMapper implements IssueMapper<GitHubIssueDto> {

    @Override
    public IssueDto map(GitHubIssueDto externalIssueDto) {
        return IssueDto.builder()
                .id(externalIssueDto.getNumber())
                .title(externalIssueDto.getTitle())
                .description(externalIssueDto.getBody())
                .assignees(externalIssueDto.getAssignees())
                .labels(externalIssueDto.getLabels())
                .build();
    }

    @Override
    public GitHubIssueDto map(IssueDto issueDto) {
        return GitHubIssueDto.builder()
                .number(issueDto.getId())
                .title(issueDto.getTitle())
                .body(issueDto.getDescription())
                .assignees(issueDto.getAssignees())
                .labels(issueDto.getLabels())
                .build();
    }
}
