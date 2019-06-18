package com.adrrriannn.issue.manager.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * Created by adrian on 17/06/19.
 */
@Builder
@EqualsAndHashCode
public class IssueDto {

    @Getter
    private String id;

    @Getter
    private String tittle;

    @Getter
    private String description;

    @Getter
    private List<String> asignees;

    @Getter
    private List<String> labels;
}
