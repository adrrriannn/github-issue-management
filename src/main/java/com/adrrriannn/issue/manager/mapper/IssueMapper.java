package com.adrrriannn.issue.manager.mapper;

import com.adrrriannn.issue.manager.dto.IssueDto;

/**
 * Created by adrian on 17/06/19.
 */
public interface IssueMapper<T> {

    IssueDto map(T externalIssueDto);
    T map(IssueDto issueDto);
}
