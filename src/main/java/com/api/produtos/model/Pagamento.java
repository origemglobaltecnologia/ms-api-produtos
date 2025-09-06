package com.api.produtos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento; // PIX, CARTAO, BOLETO

    private LocalDateTime dataPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status; // AGUARDANDO, CONFIRMADO, RECUSADO

    public Pagamento() {}

    public Pagamento(Pedido pedido, FormaPagamento formaPagamento, LocalDateTime dataPagamento, StatusPagamento status) {
        this.pedido = pedido;
        this.formaPagamento = formaPagamento;
        this.dataPagamento = dataPagamento;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }

    public LocalDateTime getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDateTime dataPagamento) { this.dataPagamento = dataPagamento; }

    public StatusPagamento getStatus() { return status; }
    public void setStatus(StatusPagamento status) { this.status = status; }
}
