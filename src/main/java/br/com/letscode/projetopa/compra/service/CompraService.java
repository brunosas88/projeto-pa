package br.com.letscode.projetopa.compra.service;

import br.com.letscode.projetopa.compra.dto.RequisicaoCompraDTO;
import br.com.letscode.projetopa.compra.dto.RespostaCompraDTO;
import br.com.letscode.projetopa.compra.model.Compra;
import br.com.letscode.projetopa.compra.repository.CompraRepository;
import br.com.letscode.projetopa.pedido.dto.PedidoDTO;
import br.com.letscode.projetopa.pedido.model.Pedido;
import br.com.letscode.projetopa.pedido.service.PedidoService;
import br.com.letscode.projetopa.produto.dto.ProdutoDTO;
import br.com.letscode.projetopa.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final PedidoService pedidoService;
    private final ProdutoService produtoService;

    public RespostaCompraDTO cadastraCompra(RequisicaoCompraDTO requisicaoCompraDTO) {
        Compra novaCompra = new Compra();
        novaCompra.setIdCompra(UUID.randomUUID().toString());
        novaCompra.setDataCompra(LocalDateTime.now());
        novaCompra.setCpfCliente(requisicaoCompraDTO.getCpf());
        novaCompra.setValorTotal(calcularValorTotalPedidos(requisicaoCompraDTO.getPedido()));
        novaCompra.getPedidos().addAll(adicionarPedidos(requisicaoCompraDTO));
        return RespostaCompraDTO.convertCompraToRespostaCompra(compraRepository.save(novaCompra));
    }

    private List<Pedido> adicionarPedidos(RequisicaoCompraDTO requisicaoCompraDTO) {
        return requisicaoCompraDTO.getPedido()
                .stream()
                .map(pedidoService::salvarPedido)
                .collect(Collectors.toList());
    }

    private Float calcularValorTotalPedidos(List<PedidoDTO> pedidosDTO) {
        Float valorTotal = 0F;
        for (PedidoDTO pedidoDTO : pedidosDTO) {
            ProdutoDTO produto = produtoService.buscarProdutoPorCodigo(pedidoDTO.getCodigoProduto());
            valorTotal = valorTotal + ( produto.getPreco() * pedidoDTO.getQtdProduto() );
        }
        return valorTotal;
    }

    public Page<RespostaCompraDTO> listaCompras (String cpf, Pageable pageable) {
        if (!Objects.equals(cpf, null)) {
            return compraRepository.findByCpfCliente(cpf, pageable).map(RespostaCompraDTO::convertCompraToRespostaCompra);
        }
        else {
            return compraRepository.findAll(pageable).map(RespostaCompraDTO::convertCompraToRespostaCompra);
        }
    }
}
