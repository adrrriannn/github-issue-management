package com.adrrriannn.issue.manager.gateway;

import com.adrrriannn.issue.manager.dto.IssueDto;
import java.util.List;

/**
 * Created by adrian on 17/06/19.
 */
public interface IssueGateway
{

    List<IssueDto> listAllIssues();

    IssueDto getIssue(String id);

    IssueDto createIssue(IssueDto issueDto);

    void deleteIssue(String id);
}
