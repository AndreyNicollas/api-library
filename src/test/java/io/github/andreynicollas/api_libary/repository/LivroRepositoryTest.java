package io.github.andreynicollas.api_libary.repository;

import io.github.andreynicollas.api_libary.model.Autor;
import io.github.andreynicollas.api_libary.model.GeneroLivro;
import io.github.andreynicollas.api_libary.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("010101-020202");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Ciencias");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = autorRepository
                .findById(UUID.fromString("a730a9c2-849b-465c-a28d-56690922a2eb"))
                .orElse(null);

        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("12345-67890");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Novamente, um outro Livro");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = new Autor();
        autor.setNome("Maria José João Silva e Silva");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2000, 4, 27));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("12345-67890");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = new Autor();
        autor.setNome("Andrey Nicollas");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2000, 4, 27));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("2a9fc1b1-6aed-41cd-896f-4069465d53a9");
        var livroAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("260267b9-7038-4666-82b3-fc7ae6a36c56");
        Autor andrey = autorRepository.findById(idAutor).orElse(null);

        livroAtualizar.setAutor(andrey);

        repository.save(livroAtualizar);
    }

    @Test
    void deletar() {
        UUID id = UUID.fromString("1d0fd8ae-fbbd-4673-9016-eff91546c7da");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("09464794-803b-40f3-8169-adf30b15f037");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
    }

    @Test
    void pesquisarPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("O roubo da casa de doce");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorIsbnTest() {
        List<Livro> lista = repository.findByIsbn("00045-67890");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloEPrecoTest() {
        var preco = BigDecimal.valueOf(700);
        var tituloPesquisa = "O dia sem noite";

        List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisa, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL() {
        var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros() {
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepetidosDosLivros() {
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresBrasileiros() {
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest() {
        var resultado = repository.findByGenero(GeneroLivro.FICCAO, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroPositionParamTest() {
        var resultado = repository.findByGeneroPositionalParameters(GeneroLivro.FICCAO, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest() {
        repository.deleteByGenero(GeneroLivro.FICCAO);
    }

    @Test
    void updateDataPublicacaoTest() {
        repository.updateDataPublicacao(LocalDate.of(2000,1,1));
    }
}