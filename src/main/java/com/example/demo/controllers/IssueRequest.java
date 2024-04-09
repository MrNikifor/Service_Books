package com.example.demo.controllers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Запрос на выдачу")
public class IssueRequest {
    @Schema(name = "Номер читателя")
    private long readerId;
    @Schema(name = "Номер книги")
    private long bookId;
}
