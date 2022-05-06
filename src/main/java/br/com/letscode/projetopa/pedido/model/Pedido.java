package br.com.letscode.projetopa.pedido.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pedido {
    private String codigo;
    private Integer quantidade;
    private Float valor;
}
