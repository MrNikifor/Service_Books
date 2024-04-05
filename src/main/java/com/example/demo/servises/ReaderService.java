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
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public List<Reader> getAllReader() {
        List<Reader> readers = readerRepository.findAll();
        if(readers.isEmpty()){
            log.error("Читатель ненайден!");
            throw new NoSuchElementException("Читатель ненайден!");
        }
        return readers;
    }
    public Reader getById(Long id){
       return readerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Читатели не найдены!"));

    }
    public void remove(Long id){
        readerRepository.deleteById(id);
    }
    public List<Issue> getAllByReaderId(Long id){
        return issueRepository.findAllByReaderId(id);
    }
    public void updateReader(Reader reader){
        Reader newReader = new Reader();
        newReader.setId(reader.getId());
        newReader.setName(reader.getName());
        newReader.setCountOfBooks(reader.getCountOfBooks());
        readerRepository.save(newReader);
    }
}
