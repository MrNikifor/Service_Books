package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Issue;
import com.example.demo.entity.Reader;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderRepository {
    private List<Reader> list = new ArrayList<>();

    public ReaderRepository() {
        list.add(new Reader("Миша"));
        list.add(new Reader("Галя"));
        list.add(new Reader("Евгений"));

    }

    public Reader findById(long id) {
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Reader> getAllReader() {
        return List.copyOf(list);
    }

    public void remove(long id){
        Reader reader = findById(id);
        list.remove(list.indexOf(reader));
    }
}
