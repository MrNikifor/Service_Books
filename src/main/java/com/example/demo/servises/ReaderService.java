package com.example.demo.servises;

import com.example.demo.entity.Issue;
import com.example.demo.entity.Reader;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public List<Reader> getAllReader() {
        List<Reader> readers = readerRepository.getAllReader();
        if(readers.isEmpty()){
            log.error("Читатель ненайден!");
            throw new NoSuchElementException("Читатель ненайден!");
        }
        return readers;
    }
    public Reader getById(long id){
        Reader reader = readerRepository.findById(id);
        if(reader== null){
            log.error("Читатели не найдены!");
            throw new NoSuchElementException("Читатели не найдены!");
        }
        return reader;
    }
    public void remove(long id){
        readerRepository.remove(id);
    }
    public List<Issue> getAllByReaderId(long id){
        return issueRepository.getAllByReaderId(id);
    }
}
