package com.example.demo.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Issue {
    private static long genId;

    private final long id;
    private final long idReader;
    private final long idBook;
    private final LocalDateTime issued_at;
    private  LocalDateTime returned_at;

    public Issue(long idReader, long idBook, LocalDateTime issued_at) {
        id = genId++;
        this.idReader = idReader;
        this.idBook = idBook;
        this.issued_at = issued_at;
    }
}
