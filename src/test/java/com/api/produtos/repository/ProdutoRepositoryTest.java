package com.api.produtos.repository;

import com.api.produtos.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void testNomeCannotBeNull() {
        Produto produto = new Produto();
        produto.setNome(null);
        produto.setDescricao("Descrição teste");
        produto.setPreco(10.0);
        produto.setQuantidade(5);

        assertThrows(ConstraintViolationException.class, () -> {
            produtoRepository.saveAndFlush(produto);
        });
    }

    @Test
    void testDescricaoCannotBeNull() {
        Produto produto = new Produto();
        produto.setNome("Produto teste");
        produto.setDescricao(null);
        produto.setPreco(10.0);
        produto.setQuantidade(5);

        assertThrows(ConstraintViolationException.class, () -> {
            produtoRepository.saveAndFlush(produto);
        });
    }

    @Test
    void testPrecoCannotBeNull() {
        Produto produto = new Produto();
        produto.setNome("Produto teste");
        produto.setDescricao("Descrição teste");
        produto.setPreco(null);
        produto.setQuantidade(5);

        assertThrows(ConstraintViolationException.class, () -> {
            produtoRepository.saveAndFlush(produto);
        });
    }

    @Test
    void testQuantidadeCannotBeNull() {
        Produto produto = new Produto();
        produto.setNome("Produto teste");
        produto.setDescricao("Descrição teste");
        produto.setPreco(10.0);
        produto.setQuantidade(null);

        assertThrows(ConstraintViolationException.class, () -> {
            produtoRepository.saveAndFlush(produto);
        });
    }
}
