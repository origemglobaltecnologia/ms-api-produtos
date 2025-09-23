package com.api.produtos.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

class ProdutoTest {

    @Test
    void testIdIsNullByDefault() {
        Produto p = new Produto();
        assertNull(p.getId(), "ID deve ser null por padrão");
    }

    @Test
    void testNomeIsNullByDefault() {
        Produto p = new Produto();
        assertNull(p.getNome(), "Nome deve ser null por padrão");
    }

    @Test
    void testDescricaoIsNullByDefault() {
        Produto p = new Produto();
        assertNull(p.getDescricao(), "Descricao deve ser null por padrão");
    }

    @Test
    void testPrecoIsNullByDefault() {
        Produto p = new Produto();
        assertNull(p.getPreco(), "Preco deve ser null por padrão");
    }

    @Test
    void testQuantidadeIsNullByDefault() {
        Produto p = new Produto();
        assertNull(p.getQuantidade(), "Quantidade deve ser null por padrão");
    }

    @Test
    void testGettersAndSetters() {
        Produto p = new Produto();
        UUID id = UUID.randomUUID();
        p.setId(id);
        p.setNome("Produto A");
        p.setDescricao("Descricao A");
        p.setPreco(10.0);
        p.setQuantidade(5);

        assertEquals(id, p.getId());
        assertEquals("Produto A", p.getNome());
        assertEquals("Descricao A", p.getDescricao());
        assertEquals(10.0, p.getPreco());
        assertEquals(5, p.getQuantidade());
    }

    @Test
    void testConstructor() {
        Produto p = new Produto("Produto B", "Descricao B", 20.0, 10);
        assertEquals("Produto B", p.getNome());
        assertEquals("Descricao B", p.getDescricao());
        assertEquals(20.0, p.getPreco());
        assertEquals(10, p.getQuantidade());
        assertNull(p.getId());
    }

    @Test
    void testEqualsAndHashCode() {
        Produto p1 = new Produto();
        Produto p2 = new Produto();
        UUID id = UUID.randomUUID();
        p1.setId(id);
        p2.setId(id);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testToString() {
        Produto p = new Produto("Nome", "Desc", 10.0, 1);
        String s = p.toString();
        assertTrue(s.contains("Nome"));
        assertTrue(s.contains("Desc"));
        assertTrue(s.contains("10.0"));
        assertTrue(s.contains("1"));
    }
}
