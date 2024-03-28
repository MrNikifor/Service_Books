package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Issue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class BookRepository {
    private List<Book> list = new ArrayList<>();

    public BookRepository() {
        list.add(new Book("Война и мир"));
        list.add(new Book("Мастер и Маргарита"));
        list.add(new Book("Приключение Буратино"));
    }

    public Book findById(long id){
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Book> getAllBooks(){
        return List.copyOf(list);
    }
    public void remove(long id){
        Book book = findById(id);
        list.remove(list.indexOf(book));
    }
}
