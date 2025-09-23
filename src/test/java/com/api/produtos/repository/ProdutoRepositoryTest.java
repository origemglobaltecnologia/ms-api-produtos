package com.api.produtos.repository;

import com.api.produtos.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void testSaveAndFindById() {
        Produto p = new Produto("Produto Test", "Desc", 10.0, 2);
        Produto saved = produtoRepository.save(p);

        Optional<Produto> found = produtoRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals(saved.getNome(), found.get().getNome());
    }

    @Test
    void testFindAll() {
        produtoRepository.save(new Produto("P1", "D1", 5.0, 1));
        produtoRepository.save(new Produto("P2", "D2", 15.0, 3));

        List<Produto> produtos = produtoRepository.findAll();
        assertTrue(produtos.size() >= 2);
    }

    @Test
    void testDelete() {
        Produto p = produtoRepository.save(new Produto("Delete", "Desc", 1.0, 1));
        UUID id = p.getId();
        assertTrue(produtoRepository.existsById(id));

        produtoRepository.deleteById(id);
        assertFalse(produtoRepository.existsById(id));
    }

    // Novos testes para verificar atributos null
    @Test
    void testNomeCannotBeNull() {
        Produto p = new Produto(null, "Desc", 10.0, 1);
        assertThrows(DataIntegrityViolationException.class, () -> produtoRepository.saveAndFlush(p));
    }

    @Test
    void testDescricaoCannotBeNull() {
        Produto p = new Produto("Nome", null, 10.0, 1);
        assertThrows(DataIntegrityViolationException.class, () -> produtoRepository.saveAndFlush(p));
    }

    @Test
    void testPrecoCannotBeNull() {
        Produto p = new Produto("Nome", "Desc", null, 1);
        assertThrows(DataIntegrityViolationException.class, () -> produtoRepository.saveAndFlush(p));
    }

    @Test
    void testQuantidadeCannotBeNull() {
        Produto p = new Produto("Nome", "Desc", 10.0, null);
        assertThrows(DataIntegrityViolationException.class, () -> produtoRepository.saveAndFlush(p));
    }
}
