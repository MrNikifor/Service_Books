package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "readers")
@Schema(name = "Читатель")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(name = "Индефикационный номер читателя")
    private Long id;

    @Column(name = "reader_name")
    @Schema(name = "Читатель")
    private String name;
    @Column(name = "count_of_books")
    @Schema(name = "Количество книг у читателя")
    private int countOfBooks = 0;

}
