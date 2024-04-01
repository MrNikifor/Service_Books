package com.example.demo.controllers;

import com.example.demo.entity.Book;
import com.example.demo.entity.Reader;
import com.example.demo.servises.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HtmlReaderController {

    private final ReaderService readerService;

    @GetMapping("/ui/readers")
    public String getAllReader(Model model){
        List<Reader> readerList = readerService.getAllReader();
        model.addAttribute("readers",readerList);
        return "readers";
    }
}
