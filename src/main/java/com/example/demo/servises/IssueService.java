package com.example.demo.servises;

import com.example.demo.controllers.IssueRequest;
import com.example.demo.controllers.IssueResponse;
import com.example.demo.entity.Book;
import com.example.demo.entity.Issue;
import com.example.demo.entity.Reader;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    private final ReaderService readerService;
    @Value("${application.issue.max-allowed-books}")
    private int maxCountOfBooks;

    public IssueResponse creatIssue(IssueRequest request) throws IllegalAccessException {
        Reader reader = readerService.getById(request.getReaderId());

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NoSuchElementException("Книга не найдена!"));

        if (isReaderHaveBook(request)) {
            log.error("У читателя уже есть книга!");
            throw new IllegalAccessException("У читателя уже есть книга!");
        }

        Issue issue = new Issue(reader, List.of(book));
        issueRepository.save(issue);

        reader.setCountOfBooks(reader.getCountOfBooks() + 1);
        readerService.updateReader(reader);

        return createResponse(request);
    }

    private int countCheck(int max) {
        return max == 0 ? 1 : max;
    }

    private IssueResponse createResponse(IssueRequest issue) {
        String readerName = readerService.getById(issue.getReaderId()).getName();
        String bookName = bookRepository.findById(issue.getBookId()).get().getName();

        IssueResponse response = new IssueResponse();
        response.setReaderName(readerName);
        response.setBookName(bookName);

        response.setIssued_at(LocalDateTime.now());

        return response;
    }

    public Issue getById(Long id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Книга не выдана!"));
    }

    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    public boolean isReaderHaveBook(IssueRequest issueRequest) {
        Long readerId = issueRequest.getReaderId();
        int readerBooks = 0;

        for (Issue issue : getAll()) {
            if (issue.getReader().getId().equals(readerId) && issue.getReturned_at() == null) {
                readerBooks++;
            }
        }
        if (readerBooks >= countCheck(maxCountOfBooks)) {
            log.error("У читателя слишком много книг!");
            return true;
        }
        return false;
    }

    public void returnBook(Long id) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Нет такого заказа!"));

        Issue newIssue = new Issue();
        newIssue.setId(issue.getId());
        newIssue.setBooks(issue.getBooks());
        newIssue.setReader(issue.getReader());
        newIssue.setCreated_at(issue.getCreated_at());
        newIssue.setReturned_at(LocalDateTime.now());

        issueRepository.save(newIssue);
    }

}
