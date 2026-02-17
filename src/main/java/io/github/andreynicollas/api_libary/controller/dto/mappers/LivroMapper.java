package io.github.andreynicollas.api_libary.controller.dto.mappers;

import io.github.andreynicollas.api_libary.controller.dto.CadastroLivroDto;
import io.github.andreynicollas.api_libary.model.Livro;
import io.github.andreynicollas.api_libary.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDto dto);
}
