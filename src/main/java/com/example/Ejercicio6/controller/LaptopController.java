package com.example.Ejercicio6.controller;

import com.example.Ejercicio6.entitie.Laptop;
import com.example.Ejercicio6.repository.LaptopRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        //Recuperar todos los libros de la base de datos
        return laptopRepository.findAll();
    }

    //Crear un libro
    @PostMapping("/api/laptops")
    public Laptop create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        //Almacenar un libro en la base de datos
        System.out.println(headers.get("User-Agent"));
        return laptopRepository.save(laptop);
    }
}
