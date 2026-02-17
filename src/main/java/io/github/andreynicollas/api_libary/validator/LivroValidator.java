package io.github.andreynicollas.api_libary.validator;

import io.github.andreynicollas.api_libary.exceptions.ResgistroDuplicadoException;
import io.github.andreynicollas.api_libary.model.Livro;
import io.github.andreynicollas.api_libary.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private final LivroRepository repository;

    public void validar(Livro livro) {
        if (existeLivroComIsbn(livro)) {
            throw new ResgistroDuplicadoException("ISBN já cadastrado!");
        }
    }

    private boolean existeLivroComIsbn(Livro livro) {
        Optional<Livro> livroEncontrado = repository.findByIsbn(livro.getIsbn());

        if (livro.getId() == null) {
            return livroEncontrado.isPresent();
        }

        return livroEncontrado
                .map(Livro::getId)
                .stream()
                .anyMatch(id -> !id.equals(livro.getId()));
    }

}
