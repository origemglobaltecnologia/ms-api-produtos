package com.api.produtos.repository;

import com.api.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    // Consultas personalizadas podem ser adicionadas aqui
}
