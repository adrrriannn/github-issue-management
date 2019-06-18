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
    private String id;

    @Getter
    private String tittle;

    @Getter
    private String body;

    @Getter
    private List<String> asignees;

    @Getter
    private List<String> labels;
}
