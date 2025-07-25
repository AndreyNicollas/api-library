package io.github.andreynicollas.api_libary.service;

import io.github.andreynicollas.api_libary.model.Autor;
import io.github.andreynicollas.api_libary.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Autor salvarAutor(Autor autor) {
        return repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }
}
