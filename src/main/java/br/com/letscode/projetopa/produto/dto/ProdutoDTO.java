package br.com.letscode.projetopa.produto.dto;

import br.com.letscode.projetopa.produto.model.Produto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoDTO {
    private String codigo;
    private Float preco;
    private Integer qtdDisponivel;

    public static ProdutoDTO convertModelToDTO(Produto produto) {
        return new ProdutoDTO(produto.getCodigo(), produto.getPreco(), produto.getQtdDisponivel());
    }

}
