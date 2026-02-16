package io.github.andreynicollas.api_libary.controller.dto.mappers;


import io.github.andreynicollas.api_libary.controller.dto.AutorDTO;
import io.github.andreynicollas.api_libary.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDto(Autor autor);
}
