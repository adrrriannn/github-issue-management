package com.adrrriannn.issue.manager.dto;

import lombok.*;

import java.util.List;

/**
 * Created by adrian on 17/06/19.
 */
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class IssueDto {

    @Getter
    private String id;

    @Getter
    private String title;

    @Getter
    private String description;

    @Getter
    private List<String> assignees;

    @Getter
    private List<String> labels;
}
