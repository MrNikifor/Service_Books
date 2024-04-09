package com.example.demo.controllers;

import com.example.demo.entity.Book;
import com.example.demo.servises.BookService;
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
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "get all books", description = "Загружает все книги, которые есть в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> bookList = bookService.getAllBooks();
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.error("Нет книг!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBuId(@PathVariable long id) {
        try {
            Book book = bookService.getById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.error("Нет книги!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeById(@PathVariable long id) {
        bookService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}




