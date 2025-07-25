package io.github.andreynicollas.api_libary.controller;

import io.github.andreynicollas.api_libary.controller.dto.AutorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores") // localhost:8080/autores
public class AutorController {

    @PostMapping
    public ResponseEntity salvarAutor(@RequestBody AutorDTO autor) {
        return new ResponseEntity("Autor salvo com sucesso!" + autor, HttpStatus.CREATED);
    }
}
