package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "books")
@NoArgsConstructor
@Schema(name = "Книга")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(name = "Индефикационный номер книги")
    private Long id;

    @Column(name = "book_name")
    @Schema(name = "Название книги")
    private String name;
}
