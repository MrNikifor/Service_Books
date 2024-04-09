package com.example.demo.servises;


import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getById(Long id){
       return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Данная книга не найденя!"));
    }
    public void remove(Long id){
        bookRepository.deleteById(id);
    }
}
