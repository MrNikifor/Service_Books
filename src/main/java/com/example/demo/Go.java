package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Для ресурсов, возвращающих HTML-страницы, реализовать авторизацию через login-форму.
Остальные /api ресурсы, возвращающие JSON, закрывать не нужно!*/
@SpringBootApplication
public class Go {

	public static void main(String[] args) {
		SpringApplication.run(Go.class, args);
	}

}
