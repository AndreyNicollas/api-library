package io.github.andreynicollas.api_libary.repository;

import io.github.andreynicollas.api_libary.model.Autor;
import io.github.andreynicollas.api_libary.model.GeneroLivro;
import io.github.andreynicollas.api_libary.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("André Gustavo");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2001, 8, 10));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void autalizarTest() {
        var id = UUID.fromString("e53c75c2-4393-4587-8da7-b27ff139839b");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();

            System.out.println("Dados do autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960,1, 30));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> list = repository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("6b65dd58-cfc5-4dbd-8b18-85059466b93e");
        repository.deleteById(id);
    }

    @Test
    public void deletePorObjetoTest() {
        var id = UUID.fromString("273056ff-27c0-4645-b28a-8027b326ca26");
        Autor maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Maria Francisca");
        autor.setNacionalidade("Maranhense");
        autor.setDataNascimento(LocalDate.of(1965, 4, 28));

        Livro livro = new Livro();
        livro.setIsbn("12345-67890");
        livro.setPreco(BigDecimal.valueOf(500));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("O roubo da casa de doce");
        livro.setDataPublicacao(LocalDate.of(1900,1,2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("00045-67890");
        livro2.setPreco(BigDecimal.valueOf(700));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("O dia sem noite");
        livro2.setDataPublicacao(LocalDate.of(2000,1,2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor() {
        var id = UUID.fromString("260267b9-7038-4666-82b3-fc7ae6a36c56");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}
