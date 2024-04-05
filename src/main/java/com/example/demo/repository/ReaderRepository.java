package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Issue;
import com.example.demo.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

}
