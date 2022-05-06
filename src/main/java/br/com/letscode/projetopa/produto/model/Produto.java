package br.com.letscode.projetopa.produto.model;

import br.com.letscode.projetopa.produto.dto.ProdutoDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "produtos")
public class Produto {
    @Id
    private String id;

    private String codigo;
    private Float preco;
    private Integer qtdDisponivel;

    public static Produto convertDTOtoModel(ProdutoDTO dto) {
        return Produto.builder().
                id(UUID.randomUUID().toString()).
                codigo(dto.getCodigo()).
                preco(dto.getPreco()).
                qtdDisponivel(dto.getQtdDisponivel()).
                build();
    }

}
