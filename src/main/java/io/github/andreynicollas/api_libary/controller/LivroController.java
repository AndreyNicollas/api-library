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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDto dto) {
        try {
            Livro livro = mapper.toEntity(dto);
            livroService.salvar(livro);
            var url = gerarHeaderLocation(livro.getId());
            return ResponseEntity.created(url).build();
        } catch (ResgistroDuplicadoException e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }
}
