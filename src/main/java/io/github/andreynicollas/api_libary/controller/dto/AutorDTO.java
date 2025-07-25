package io.github.andreynicollas.api_libary.controller.dto;

import io.github.andreynicollas.api_libary.model.Autor;

import java.time.LocalDate;

public record AutorDTO(String nome, LocalDate dataNascimento, String nacionalidade) {

    public Autor mapearAutor() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
