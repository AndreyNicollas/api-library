package io.github.andreynicollas.api_libary.service;

import io.github.andreynicollas.api_libary.model.Autor;
import io.github.andreynicollas.api_libary.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Autor salvarAutor(Autor autor) {
        return repository.save(autor);
    }
}
