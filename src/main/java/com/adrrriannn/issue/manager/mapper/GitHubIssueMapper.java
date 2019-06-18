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
                .id(externalIssueDto.getId())
                .tittle(externalIssueDto.getTittle())
                .description(externalIssueDto.getBody())
                .asignees(externalIssueDto.getAsignees())
                .labels(externalIssueDto.getLabels())
                .build();
    }

    @Override
    public GitHubIssueDto map(IssueDto issueDto) {
        return GitHubIssueDto.builder()
                .id(issueDto.getId())
                .tittle(issueDto.getTittle())
                .body(issueDto.getDescription())
                .asignees(issueDto.getAsignees())
                .labels(issueDto.getLabels())
                .build();
    }
}
