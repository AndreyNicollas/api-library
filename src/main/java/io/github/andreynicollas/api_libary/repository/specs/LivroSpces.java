package io.github.andreynicollas.api_libary.repository.specs;

import io.github.andreynicollas.api_libary.model.GeneroLivro;
import io.github.andreynicollas.api_libary.model.Livro;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpces {

    public static Specification<Livro> isbnEqual(String isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Livro> tituloLike(String titulo) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    public static Specification<Livro> generoEqual(GeneroLivro genero) {
        return (root, query, cb) -> cb.equal(root.get("genero"), genero);
    }

    public static Specification<Livro> anoPublicacaoEqual(Integer anoPublicacao) {
        // and to_char(data_publicacao, 'YYYY') = :anoPublicacao
        return (root, query, cb) ->
                cb.equal(cb.function("to_char", String.class,
                        root.get("dataPublicacao"), cb.literal("YYYY")), anoPublicacao);
    }
}
