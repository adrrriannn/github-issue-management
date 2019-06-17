package com.adrrriannn.issue.manager.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by adrian on 17/06/19.
 */
@Builder
@EqualsAndHashCode
public class IssueDto {

    @Getter
    private String id;
}
