package com.example.demo.servises;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        if (books.isEmpty()) {
            log.error("Данные книги не найдены!");
            throw new NoSuchElementException("Данные книги не найдены!");
        }
        log.info("Достаю все книги");
        return books;
    }
    public Book getById(long id){
        Book book = bookRepository.findById(id);
        if(book == null){
            log.error("Данная книга не найдена!");
            throw new NoSuchElementException("Данная книга не найденя!");
        }
        return book;
    }
    public void remove(long id){
        bookRepository.remove(id);
    }
}
