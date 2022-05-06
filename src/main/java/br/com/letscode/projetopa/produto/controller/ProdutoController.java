package br.com.letscode.projetopa.produto.controller;



import br.com.letscode.projetopa.produto.dto.ProdutoDTO;
import br.com.letscode.projetopa.produto.model.Produto;
import br.com.letscode.projetopa.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrarProduto (@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.cadastrarProduto(produtoDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listarProdutos (@RequestParam @Nullable String codigo, Pageable pageable) {
        return ResponseEntity.ok(produtoService.listarProdutos(codigo, pageable));
    }
}
