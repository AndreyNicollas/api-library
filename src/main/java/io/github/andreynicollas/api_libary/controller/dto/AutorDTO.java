package io.github.andreynicollas.api_libary.controller.dto;

import io.github.andreynicollas.api_libary.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(UUID id,
                       @NotBlank(message = "Campo obrigatório!")
                       String nome,
                       @NotNull(message = "Campo obrigatório!")
                       LocalDate dataNascimento,
                       @NotBlank(message = "Campo obrigatório!")
                       String nacionalidade) {

    public Autor mapearAutor() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
