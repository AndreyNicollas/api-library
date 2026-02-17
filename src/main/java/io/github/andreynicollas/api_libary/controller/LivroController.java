package io.github.andreynicollas.api_libary.controller;

import io.github.andreynicollas.api_libary.controller.dto.CadastroLivroDto;
import io.github.andreynicollas.api_libary.controller.dto.ErroResposta;
import io.github.andreynicollas.api_libary.controller.dto.mappers.LivroMapper;
import io.github.andreynicollas.api_libary.exceptions.ResgistroDuplicadoException;
import io.github.andreynicollas.api_libary.model.Livro;
import io.github.andreynicollas.api_libary.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDto dto) {
        try {
            Livro livro = mapper.toEntity(dto);
            livroService.salvar(livro);
            // criar url para acesso dos dados do livro
            // retornar codigo created com header location
            return ResponseEntity.ok(livro);
        } catch (ResgistroDuplicadoException e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }
}
