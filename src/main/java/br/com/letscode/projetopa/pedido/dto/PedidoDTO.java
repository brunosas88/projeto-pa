package br.com.letscode.projetopa.pedido.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private String codigoProduto;
    private Integer qtdProduto;
}
