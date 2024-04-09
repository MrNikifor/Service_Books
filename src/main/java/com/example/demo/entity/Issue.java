package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "issues")
@NoArgsConstructor
@Entity
@Schema(name = "Карточка заказа")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    @Schema(name = "Индефикационный номер книги")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "reader_id")
    @Schema(name = "Читатель")
    private Reader reader;
    @ManyToMany
    @Column(name = "book_id")
    @Schema(name = "Книга")
    private List<Book> books;
    @CreationTimestamp
    @Column(name = "created_at")
    @Schema(name = "Дата создания заказа")
    private LocalDateTime created_at;
    @Timestamp
    @Column(name = "returned_at")
    @Schema(name = "Дата возврата заказа")
    private  LocalDateTime returned_at;


    public Issue(Reader reader, List<Book> books) {
        this.reader = reader;
        this.books = books;
        this.created_at = LocalDateTime.now();
    }
}
