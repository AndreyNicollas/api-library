package io.github.andreynicollas.api_libary.repository;

import io.github.andreynicollas.api_libary.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
