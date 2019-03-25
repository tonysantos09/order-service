package br.com.fiap.orderservice;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private String email;
    private String nome;
    private String endereco;
    private Itens[] itemPedido;
    private BigDecimal total;
    private String formaPagamento;
    private String data;
    private String status;
    private String idOrder;
    private String nroCartao;
    private String valCartao;
    private String cartaoBandeira;
}
