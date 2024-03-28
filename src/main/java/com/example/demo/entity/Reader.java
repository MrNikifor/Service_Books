package com.example.demo.entity;

import lombok.Data;

@Data
public class Reader {
    private static long genId;

    private final long id;
    private final String name;
    private int countOfBooks;

    public Reader(String name) {
        id = genId++;
        this.name = name;
    }
}
