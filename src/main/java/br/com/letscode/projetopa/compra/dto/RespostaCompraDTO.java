package br.com.letscode.projetopa.compra.dto;

import br.com.letscode.projetopa.compra.model.Compra;
import br.com.letscode.projetopa.pedido.model.Pedido;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RespostaCompraDTO {

    private String dataCompra;
    private String cpfCliente;
    private Float valorTotal;
    private List<Pedido> pedidos = new ArrayList<>();

    public static RespostaCompraDTO convertCompraToRespostaCompra(Compra compra) {
        RespostaCompraDTO respostaCompraDTO = new RespostaCompraDTO();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        respostaCompraDTO.setDataCompra(compra.getDataCompra().format(formato));
        respostaCompraDTO.setCpfCliente(compra.getCpfCliente());
        respostaCompraDTO.setValorTotal(compra.getValorTotal());
        respostaCompraDTO.setPedidos(compra.getPedidos());
        return respostaCompraDTO;
    }



}
