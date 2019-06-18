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
    public IssueDto getIssue(@PathVariable("id") String id, @RequestParam("user") String user, @RequestParam("repo") String repo) {
        return issueService.getIssue(user, repo, id);
    }

    @GetMapping
    public List<IssueDto> getAllIssues(@RequestParam("user") String user, @RequestParam("repo") String repo) {
        return issueService.getAllIssues(user, repo);
    }

}
