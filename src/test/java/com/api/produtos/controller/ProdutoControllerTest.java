package com.api.produtos.controller;

import com.api.produtos.model.Produto;
import com.api.produtos.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCriarProduto() throws Exception {
        Produto p = new Produto("Produto", "Desc", 10.0, 2);
        when(produtoRepository.save(any(Produto.class))).thenReturn(p);

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Produto"));
    }

    @Test
    void testCriarProdutoComNomeNull() throws Exception {
        Produto p = new Produto(null, "Desc", 10.0, 2);
        when(produtoRepository.save(any(Produto.class))).thenThrow(new RuntimeException("Nome não pode ser null"));

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testListarProdutos() throws Exception {
        Produto p1 = new Produto("P1", "D1", 5.0, 1);
        Produto p2 = new Produto("P2", "D2", 10.0, 2);
        when(produtoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        mockMvc.perform(get("/api/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testBuscarProdutoPorId() throws Exception {
        UUID id = UUID.randomUUID();
        Produto p = new Produto("Produto", "Desc", 10.0, 2);
        p.setId(id);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(p));

        mockMvc.perform(get("/api/produtos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

    @Test
    void testAtualizarProduto() throws Exception {
        UUID id = UUID.randomUUID();
        Produto p = new Produto("Old", "Desc", 5.0, 1);
        p.setId(id);
        Produto updated = new Produto("New", "Desc", 10.0, 2);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(p));
        when(produtoRepository.save(any(Produto.class))).thenReturn(updated);

        mockMvc.perform(put("/api/produtos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("New"));
    }

    @Test
    void testExcluirProduto() throws Exception {
        UUID id = UUID.randomUUID();
        when(produtoRepository.existsById(id)).thenReturn(true);
        doNothing().when(produtoRepository).deleteById(id);

        mockMvc.perform(delete("/api/produtos/{id}", id))
                .andExpect(status().isNoContent());
    }
}
