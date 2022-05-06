package br.com.letscode.projetopa.pedido.service;

import br.com.letscode.projetopa.compra.model.Compra;
import br.com.letscode.projetopa.pedido.dto.PedidoDTO;
import br.com.letscode.projetopa.pedido.model.Pedido;
import br.com.letscode.projetopa.produto.dto.ProdutoDTO;
import br.com.letscode.projetopa.produto.model.Produto;
import br.com.letscode.projetopa.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final ProdutoService produtoService;

    public Pedido salvarPedido(PedidoDTO pedidoDTO) {
        Pedido novoPedido = new Pedido();
        ProdutoDTO produto = controleEstoqueVenda(pedidoDTO.getCodigoProduto(), pedidoDTO.getQtdProduto());
        novoPedido.setCodigo(pedidoDTO.getCodigoProduto());
        novoPedido.setQuantidade(pedidoDTO.getQtdProduto());
        novoPedido.setValor(produto.getPreco());
        return novoPedido;
    }

    public ProdutoDTO controleEstoqueVenda (String codigo, Integer qtdRetirada) {
        ProdutoDTO produtoEstoque = produtoService.buscarProdutoPorCodigo(codigo);
        if (produtoEstoque.getQtdDisponivel() < qtdRetirada) {
            throw new RuntimeException("Quantidade indisponível para compra");
        }
        return produtoService.atualizarProduto(produtoEstoque, qtdRetirada);
    }
}
