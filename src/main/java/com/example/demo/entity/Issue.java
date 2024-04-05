package com.example.demo.entity;

import jakarta.persistence.*;
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
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;
    @ManyToMany
    @JoinColumn(name = "book_id")
    private List<Book> books;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @UpdateTimestamp
    @Column(name = "returned_at")
    private  LocalDateTime returned_at;


    public Issue(Reader reader, List<Book> books) {
        this.reader = reader;
        this.books = books;
        this.created_at = LocalDateTime.now();
    }
}
