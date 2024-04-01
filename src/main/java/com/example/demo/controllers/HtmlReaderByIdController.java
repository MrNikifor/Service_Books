package com.example.demo.controllers;

import com.example.demo.servises.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HtmlReaderByIdController {
    private final ReaderService readerService;
    @GetMapping("/ui/reader/{id}")
    public String getReaderById(Model model,@PathVariable long id){
        model.addAttribute("reader",readerService.getById(id));
        return "reader_id";
    }
}
