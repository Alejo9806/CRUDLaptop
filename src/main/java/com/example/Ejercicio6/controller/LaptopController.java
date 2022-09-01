package com.example.Ejercicio6.controller;

import com.example.Ejercicio6.entitie.Laptop;
import com.example.Ejercicio6.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;
    private final Logger log = LoggerFactory.getLogger(LaptopRepository.class);

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        //Recuperar todos los libros de la base de datos
        return laptopRepository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar una laptop por clave primaria id Long") //Decorators para documentacion de la API REST Swagger
    public ResponseEntity<Laptop> findById(@ApiParam("Clave primaria tipo Long") @PathVariable Long id){
        //Recuperar un libro de la base de datos
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        //Opcion 1
        if(optionalLaptop.isPresent()){
            return ResponseEntity.ok(optionalLaptop.get());
        }
        return optionalLaptop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear un libro
    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        //Almacenar un pc en la base de datos
        System.out.println(headers.get("User-Agent"));
        if (laptop.getId() != null){ // quiere decir existe el id y por tanto no es una creacion de un libro nuevo
            log.warn("Intento de crear un pc con id existente");
            System.out.println("Intento de crear un pc con id existente");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop,@PathVariable Long id){
        //Actualizar un pc en la base de datos
        if (laptop.getId() == null){
            log.warn("Intento de actualizar un pc con id inexistente");
            System.out.println("Intento de actualizar un pc con id inexistente");
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(id)){
            log.warn("Intento de actualizar un pc con id diferente al id del libro a actualizar");
            System.out.println("Intento de actualizar un pc con id diferente al id del libro a actualizar");
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    //Borrar un libro
    @ApiIgnore //Decorators para documentacion de la API REST Swagger esto es para que no aparezca en la documentacion
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if (!laptopRepository.existsById(id)){
            log.warn("Intento de borrar un libro que no existe");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiIgnore //Decorators para documentacion de la API REST Swagger esto es para que no aparezca en la documentacion
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        //Borrar todos los libros de la base de datos
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
