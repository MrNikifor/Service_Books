package com.example.demo.controllers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "Ответ о выдаче")
public class IssueResponse {
    @Schema(name = "Читателя")
    private String readerName;
    @Schema(name = "Книги")
    private String bookName;
    @Schema(name = "Время запроса")
    private LocalDateTime issued_at;
    @Schema(name = "Время возврата(обмена)")
    private LocalDateTime returned_at;

}
