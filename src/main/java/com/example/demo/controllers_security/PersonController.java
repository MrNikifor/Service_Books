/*
package com.example.demo.controllers_security;

import com.example.demo.servises.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/any")
    public String any(){
        return "any";
    }

    @GetMapping("/admin")
    public String admin(String login){
        return "admin";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping("/auth")
    public String auth(){
        return "auth";
    }
}
*/
