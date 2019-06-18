package com.adrrriannn.issue.manager.controller;

import com.adrrriannn.issue.manager.dto.IssueDto;
import com.adrrriannn.issue.manager.service.IssueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by adrian on 17/06/19.
 */
@RestController
@RequestMapping("/issues")
public class IssueController {

    private IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/{id}")
    public IssueDto getIssue(@PathVariable("id") String id) {
        return issueService.getIssue(id);
    }

    @GetMapping
    public List<IssueDto> getAllIssues() {
        return issueService.getAllIssues();
    }

    @PostMapping
    public IssueDto createIssue(IssueDto issueDto) {
        return issueService.createIssue(issueDto);
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable("id") String id) {
        issueService.deleteIssue(id);
    }
}
