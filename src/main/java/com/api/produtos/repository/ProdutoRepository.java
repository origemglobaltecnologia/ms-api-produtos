package com.api.produtos.repository;

import com.api.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interface de repositório JPA para acesso ao banco de dados
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Pode adicionar consultas personalizadas se necessário
}
