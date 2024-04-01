package com.example.demo.controllers;

import com.example.demo.entity.Issue;
import com.example.demo.entity.Reader;
import com.example.demo.servises.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HtmlIssueControllers {
    private final IssueService issueService;

    @GetMapping("/ui/issue")
    public String getAllIssue(Model model){
        List<Issue> issueList = issueService.getAll();
        model.addAttribute("issues",issueList);
        return "issues";
    }
}
