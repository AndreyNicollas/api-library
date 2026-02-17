package io.github.andreynicollas.api_libary.service;

import io.github.andreynicollas.api_libary.model.GeneroLivro;
import io.github.andreynicollas.api_libary.model.Livro;
import io.github.andreynicollas.api_libary.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return livroRepository.findById(id);
    }

    public void deletar(Livro livro) {
        livroRepository.delete(livro);
    }

    public List<Livro> pesquisa(String isbn, String nomeAutor, GeneroLivro genero, String anoPublicacao) {
        Specification<Livro> specs = null;
        return livroRepository.findAll(specs);
    }
}
