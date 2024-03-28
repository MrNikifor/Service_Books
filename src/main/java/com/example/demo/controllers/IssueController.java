package com.example.demo.controllers;

import com.example.demo.entity.Issue;
import com.example.demo.servises.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;


    @PostMapping
    public ResponseEntity<IssueResponse> issueBook(@RequestBody IssueRequest issueRequest) {
        log.info("Пропустил запрос на выдачу: readerId={}, bookId={}"
                , issueRequest.getReaderId(), issueRequest.getBookId());
        try {
            ResponseEntity<IssueResponse> response = ResponseEntity.status(HttpStatus.CREATED)
                    .body(issueService.creatIssue(issueRequest));
            log.info("Отдал книгу читателю");
            return response;
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponse> getById(@PathVariable long id) {
        try {
            IssueResponse issueResponse = issueService.getById(id);
            return new ResponseEntity<>(issueResponse,HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getAll(){
        return new ResponseEntity<>(issueService.getAll(),HttpStatus.OK);
    }
    @PutMapping("/{issueId}")
    public ResponseEntity returnBook(@PathVariable long issueId){
        issueService.returnBook(issueId);
        return  new ResponseEntity(HttpStatus.OK);
    }
}
