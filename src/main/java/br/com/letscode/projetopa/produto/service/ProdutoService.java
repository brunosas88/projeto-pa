package br.com.letscode.projetopa.produto.service;


import br.com.letscode.projetopa.produto.dto.ProdutoDTO;
import br.com.letscode.projetopa.produto.model.Produto;
import br.com.letscode.projetopa.produto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final MongoTemplate mongoTemplate;

    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        return ProdutoDTO.convertModelToDTO(produtoRepository.save(Produto.convertDTOtoModel(produtoDTO)));
    }

    public Page<ProdutoDTO> listarProdutos(String codigo, Pageable pageable) {
        if (!Objects.equals(codigo, null)) {
            return listToPage(List.of(buscarProdutoPorCodigo(codigo)),pageable);
        }
        else {
            return produtoRepository.findAll(pageable).map(ProdutoDTO::convertModelToDTO);
        }
    }

    public ProdutoDTO buscarProdutoPorCodigo(String codigo) {
        Produto produto = produtoRepository.findProdutoByCodigo(codigo);
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado");
        }
        return ProdutoDTO.convertModelToDTO(produto);
    }

    public static Page listToPage(List list, Pageable pageable) {
        if (pageable.getOffset() >= list.size()) {
            return Page.empty();
        }
        int startIndex = (int)pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ?
                list.size() :
                pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }

    public ProdutoDTO atualizarProduto(ProdutoDTO produtoEstoque, Integer qtdRetirada) {
        Query query = new Query();
        query.addCriteria(Criteria.where("codigo").is(produtoEstoque.getCodigo()));
        Update update = new Update();
        update.set("qtdDisponivel", produtoEstoque.getQtdDisponivel() - qtdRetirada);
        return  ProdutoDTO.convertModelToDTO(Objects.requireNonNull(mongoTemplate.findAndModify(query, update, Produto.class)));
    }
}
