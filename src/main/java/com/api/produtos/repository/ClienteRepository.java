package com.api.produtos.repository;

import com.api.produtos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aqui você pode adicionar métodos customizados, por exemplo:
    // Optional<Cliente> findByEmail(String email);
}
