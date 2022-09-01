package com.example.Ejercicio6.controller;

import com.example.Ejercicio6.entitie.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate restTemplate;

    @Autowired
    private RestTemplateBuilder builder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        builder = builder.rootUri("http://localhost:" + port);
        restTemplate = new TestRestTemplate(builder);
    }
    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = restTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());

        List<Laptop> books = Arrays.asList(response.getBody());
        System.out.println(books.size());
    }

    @Test
    void findById() {
        ResponseEntity<Laptop> response = restTemplate.getForEntity("/api/laptops/1", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                "name": "Asus",
                "description": "J.R.R. Tolkien",
                "price": 19.95,
                "brand": "Asus",
                "processor": "Ryzen",
                "ram": "4GB",
                "graphics": "RTX",
                }
        """;

        HttpEntity<String> request = new HttpEntity<>(json, header);
        ResponseEntity<Laptop> response = restTemplate.exchange("/api/laptops",HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1L,result.getId());
        assertEquals("Asus",result.getName());
    }
}