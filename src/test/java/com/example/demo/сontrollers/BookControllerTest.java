package com.example.demo.сontrollers;

import com.example.demo.controllers.BookController;
import com.example.demo.entity.Book;
import com.example.demo.servises.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookControllerTest {
    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllBooksTest() {
        Book book1 = new Book(1L, "Java Core");
        Book book2 = new Book(2L, "The Philosophy of Java");
        List<Book> expectedBooks = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(expectedBooks);

        List<Book> actualBooks = bookController.getAllBooks().getBody();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void getByIdTest() {
        Book expectedBook = new Book(1L, "Java Core");

        when(bookService.getById(1L)).thenReturn(expectedBook);
        Book actualBook = bookController.getBuId(1L).getBody();

        assertEquals(expectedBook, actualBook);
    }

    @Test
    public void removeByIdTest() {
        assertNull(bookController.removeById(1L).getBody());
    }

}
