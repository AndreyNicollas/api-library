package io.github.andreynicollas.api_libary.service;

import io.github.andreynicollas.api_libary.model.Livro;
import io.github.andreynicollas.api_libary.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;


    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }
}
