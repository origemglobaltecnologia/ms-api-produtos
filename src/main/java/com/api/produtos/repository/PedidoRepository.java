package com.api.produtos.repository;

import com.api.produtos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Exemplo de método personalizado:
    List<Pedido> findByCompradorId(Long compradorId);
    List<Pedido> findByStatus(String status);
}
