package io.github.andreynicollas.api_libary.service;

import io.github.andreynicollas.api_libary.model.Autor;
import io.github.andreynicollas.api_libary.model.GeneroLivro;
import io.github.andreynicollas.api_libary.model.Livro;
import io.github.andreynicollas.api_libary.repository.AutorRepository;
import io.github.andreynicollas.api_libary.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void executar() {
        Autor autor = new Autor();
        autor.setNome("Antonio Olivio Rodrigues");
        autor.setNacionalidade("Itapecuruense");
        autor.setDataNascimento(LocalDate.of(2000, 4, 7));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("55555-67890");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Um Livro de mentiras do Neto");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if (autor.getNome().equals("José")) {
            throw new RuntimeException("Rollback!");
        }
    }
}
