package com.example.Ejercicio6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Controler se usa cuando estes usando MVC y quieras devolver vistas RestControler es para json
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

}
