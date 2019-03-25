package br.com.fiap.orderservice;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Itens {
    private String descricao;
    private int qtd;
    private BigDecimal valor;
}
