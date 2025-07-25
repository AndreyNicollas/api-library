package io.github.andreynicollas.api_libary.controller.dto;

import java.time.LocalDate;

public record AutorDTO(String nome, LocalDate dataNascimento, String nacionalidade) {
}
