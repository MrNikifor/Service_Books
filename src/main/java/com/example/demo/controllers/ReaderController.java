package com.example.demo.controllers;

import com.example.demo.entity.Issue;
import com.example.demo.entity.Reader;
import com.example.demo.servises.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "get all reader", description = "Загружает всех читателей, которые есть в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Reader>> getAllReader() {
        try {
            List<Reader> readerList = readerService.getAllReader();
            return new ResponseEntity<>(readerList, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.error("Читатели ненайдены!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getById(@PathVariable long id) {
        try {
            Reader reader = readerService.getById(id);
            return new ResponseEntity<>(reader, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.error("Читатель ненайден!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable long id) {
        readerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> getAllIssueByReaderId(@PathVariable long id) {
        return new ResponseEntity<>(readerService.getAllByReaderId(id), HttpStatus.OK);
    }
}
