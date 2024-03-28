package com.example.demo.servises;

import com.example.demo.controllers.IssueRequest;
import com.example.demo.controllers.IssueResponse;
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
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    @Value("${application.issue.max-allowed-books}")
    private int maxCountOfBooks;

    public IssueResponse creatIssue(IssueRequest request) throws IllegalAccessException {
        Reader reader = readerRepository.findById(request.getReaderId());

        if (bookRepository.findById(request.getBookId()) == null) {
            log.error("Не удалось найти книгу с id " + request.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }
        if (reader == null) {
            log.error("Не удалось найти читателя с id " + request.getReaderId());
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
        }

        if (isReaderHaveBook(request)) {
            log.error("У читателя уже есть книга!");
            throw new IllegalAccessException("У читателя уже есть книга!");
        }


        Issue issue = new Issue(request.getReaderId(), request.getBookId(), LocalDateTime.now());
        issueRepository.creatIssue(issue);

        reader.setCountOfBooks(reader.getCountOfBooks() + 1);

        return createResponse(issue);
    }

    private int countCheck(int max) {
        return max == 0 ? 1 : max;
    }

    private IssueResponse createResponse(Issue issue) {
        String readerName = readerRepository.findById(issue.getIdReader()).getName();
        String bookName = bookRepository.findById(issue.getIdBook()).getName();

        IssueResponse response = new IssueResponse();
        response.setReaderName(readerName);
        response.setBookName(bookName);

        response.setIssued_at(issue.getIssued_at());
        response.setReturned_at(issue.getReturned_at());

        return response;
    }

    public IssueResponse getById(long id) {
        Issue issue = issueRepository.findById(id);
        if (issue == null) {
            log.error("Книга не выдана!");
            throw new NoSuchElementException("Книга не выдана!");
        }
        return createResponse(issue);
    }

    public List<Issue> getAll() {
        return issueRepository.getAll();
    }

    public boolean isReaderHaveBook(IssueRequest issueRequest) {
        long readerId = issueRequest.getReaderId();
        int readerBooks = 0;

        for (Issue issue : getAll()) {
            if (issue.getIdReader() == readerId && issue.getReturned_at() == null) {
                readerBooks++;
            }
        }
        if (readerBooks >= countCheck(maxCountOfBooks)) {
            log.error("У читателя слишком много книг!");
            return true;
        }
        return false;
    }

    public void returnBook(long id) {
        issueRepository.findById(id).setReturned_at(LocalDateTime.now());
    }

}
