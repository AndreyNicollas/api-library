package io.github.andreynicollas.api_libary.repository;

import io.github.andreynicollas.api_libary.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
