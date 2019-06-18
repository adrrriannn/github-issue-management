package com.adrrriannn.issue.manager.dto;

import lombok.*;

import java.util.List;

/**
 * Created by adrian on 17/06/19.
 */
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitHubIssueDto {
    @Getter
    private String number;

    @Getter
    private String title;

    @Getter
    private String body;

    @Getter
    private List<String> assignees;

    @Getter
    private List<String> labels;
}
