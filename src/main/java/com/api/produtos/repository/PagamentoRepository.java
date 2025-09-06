package com.api.produtos.repository;

import com.api.produtos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    // Exemplo de métodos customizados:
    List<Pagamento> findByStatus(String status);
    Pagamento findByPedidoId(Long pedidoId);
}
