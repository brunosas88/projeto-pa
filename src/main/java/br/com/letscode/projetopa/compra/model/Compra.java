package br.com.letscode.projetopa.compra.model;

import br.com.letscode.projetopa.pedido.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "compra")
public class Compra{
    @Id
    private String idCompra;

    private LocalDateTime dataCompra;
    private String cpfCliente;
    private Float valorTotal;
    private List<Pedido> pedidos = new ArrayList<>();
}
