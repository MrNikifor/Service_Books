package com.example.demo.controllers;

import com.example.demo.entity.Issue;
import com.example.demo.entity.Reader;
import com.example.demo.servises.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/readers")
public class ReaderController {
    private final ReaderService readerService;

    @GetMapping
    public ResponseEntity<List<Reader>> getAllReader(){
        try {
            List<Reader> readerList = readerService.getAllReader();
            return new ResponseEntity<>(readerList,HttpStatus.OK);
        }catch (NoSuchElementException e){
            log.error("Читатели ненайдены!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reader> getById(@PathVariable long id){
        try {
            Reader reader = readerService.getById(id);
            return new ResponseEntity<>(reader,HttpStatus.OK);
        }catch (NoSuchElementException e){
            log.error("Читатель ненайден!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable long id){
        readerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> getAllIssueByReaderId(@PathVariable long id){
        return new ResponseEntity<>(readerService.getAllByReaderId(id),HttpStatus.OK);
    }
}
