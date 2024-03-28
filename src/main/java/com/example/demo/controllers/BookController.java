package com.example.demo.controllers;

import com.example.demo.entity.Book;
import com.example.demo.servises.BookService;
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

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        try {
            List<Book> bookList = bookService.getAllBooks();
            return new ResponseEntity<>(bookList,HttpStatus.OK);
        }catch (NoSuchElementException e){
            log.error("Нет книг!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBuId(@PathVariable long id){
        try {
            Book book = bookService.getById(id);
            return new ResponseEntity<>(book,HttpStatus.OK);
        }catch (NoSuchElementException e){
            log.error("Нет книги!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity removeById(@PathVariable long id){
        bookService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}




