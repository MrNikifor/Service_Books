package com.example.demo.controllers;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueResponse {
    private String readerName;
    private String bookName;
    private LocalDateTime issued_at;
    private LocalDateTime returned_at;

}
