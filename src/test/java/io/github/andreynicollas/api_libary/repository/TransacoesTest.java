package io.github.andreynicollas.api_libary.repository;

import io.github.andreynicollas.api_libary.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    @Test
    void transcoesSimples() {
        transacaoService.executar();
    }
}
