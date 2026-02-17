package io.github.andreynicollas.api_libary.controller;

import io.github.andreynicollas.api_libary.controller.dto.CadastroLivroDto;
import io.github.andreynicollas.api_libary.controller.dto.ResultadoPesquisaLivroDto;
import io.github.andreynicollas.api_libary.controller.dto.mappers.LivroMapper;
import io.github.andreynicollas.api_libary.model.Livro;
import io.github.andreynicollas.api_libary.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDto dto) {
        Livro livro = mapper.toEntity(dto);
        livroService.salvar(livro);
        var url = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDto> obterDetalhes(@PathVariable("id") String id) {
        return livroService.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    var dto = mapper.toDto(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
