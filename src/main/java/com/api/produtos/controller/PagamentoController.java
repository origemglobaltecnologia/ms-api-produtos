package com.api.produtos.controller;

import com.api.produtos.model.Pagamento;
import com.api.produtos.repository.PagamentoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagamentos")
@CrossOrigin(origins = "*")
public class PagamentoController {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoController(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Pagamento> criarPagamento(@RequestBody Pagamento pagamento) {
        Pagamento novo = pagamentoRepository.save(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    // READ - listar todos
    @GetMapping
    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    // READ - buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPagamento(@PathVariable Long id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        return pagamento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable Long id, @RequestBody Pagamento pagamentoAtualizado) {
        return pagamentoRepository.findById(id).map(pagamento -> {
            pagamento.setPedido(pagamentoAtualizado.getPedido());
            pagamento.setFormaPagamento(pagamentoAtualizado.getFormaPagamento());
            pagamento.setDataPagamento(pagamentoAtualizado.getDataPagamento());
            pagamento.setStatus(pagamentoAtualizado.getStatus());
            Pagamento atualizado = pagamentoRepository.save(pagamento);
            return ResponseEntity.ok(atualizado);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPagamento(@PathVariable Long id) {
        if (pagamentoRepository.existsById(id)) {
            pagamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
