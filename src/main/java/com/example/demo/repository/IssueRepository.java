package com.example.demo.repository;

import com.example.demo.entity.Issue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IssueRepository {
    private List<Issue> list = new ArrayList<>();
    public void creatIssue(Issue issue){
        list.add(issue);
    }
    public Issue findById(long id){
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Issue> getAll(){
        return list;
    }
    public List<Issue> getAllByReaderId(long id){
        return list.stream().filter(e -> e.getIdReader() == id).collect(Collectors.toList());
    }


}
