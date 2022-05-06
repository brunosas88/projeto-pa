package br.com.letscode.projetopa.compra.dto;

import br.com.letscode.projetopa.pedido.dto.PedidoDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequisicaoCompraDTO {
    private String cpf;
    private List<PedidoDTO> pedido;
}
