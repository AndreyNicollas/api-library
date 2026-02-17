package io.github.andreynicollas.api_libary.service;

import io.github.andreynicollas.api_libary.model.GeneroLivro;
import io.github.andreynicollas.api_libary.model.Livro;
import io.github.andreynicollas.api_libary.repository.LivroRepository;
import io.github.andreynicollas.api_libary.repository.specs.LivroSpces;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.andreynicollas.api_libary.repository.specs.LivroSpces.*;

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

    public List<Livro> pesquisa(String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao) {

        // select * from livro where isbn = :isbn and nomeAutor =
//        Specification<Livro> specs = Specification
//                .where(LivroSpces.isbnEqual(isbn))
//                .and(LivroSpces.tituloLike(titulo))
//                .and(LivroSpces.generoEqual(genero));

        // select * from livro where 0 = 0
        Specification<Livro> specs = Specification
                .where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (isbn != null) {
            // query = query and isbn = :isbn
            specs = specs.and(isbnEqual(isbn));
        }

        if (titulo != null) {
            specs = specs.and(tituloLike(titulo));
        }

        if (genero != null) {
            specs = specs.and(generoEqual(genero));
        }

        if (anoPublicacao != null) {
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        if (nomeAutor != null) {
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        return livroRepository.findAll(specs);
    }
}
