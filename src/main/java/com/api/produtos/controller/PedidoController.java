package com.api.produtos.controller;

import com.api.produtos.model.Pedido;
import com.api.produtos.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novo = pedidoRepository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    // READ - listar todos
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // READ - buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setComprador(pedidoAtualizado.getComprador());
            pedido.setProduto(pedidoAtualizado.getProduto());
            pedido.setQuantidade(pedidoAtualizado.getQuantidade());
            pedido.setValorTotal(pedidoAtualizado.getValorTotal());
            pedido.setDataPedido(pedidoAtualizado.getDataPedido());
            pedido.setStatus(pedidoAtualizado.getStatus());
            Pedido atualizado = pedidoRepository.save(pedido);
            return ResponseEntity.ok(atualizado);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
