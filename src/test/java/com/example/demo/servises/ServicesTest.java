package com.example.demo.servises;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServicesTest {
    @Autowired
    private BookService bookService;


    @Test
    public void getAllBooksTest() {
        assertFalse(bookService.getAllBooks().isEmpty());
    }
}
