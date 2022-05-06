package br.com.letscode.projetopa.compra.controller;



import br.com.letscode.projetopa.compra.dto.RequisicaoCompraDTO;
import br.com.letscode.projetopa.compra.dto.RespostaCompraDTO;
import br.com.letscode.projetopa.compra.model.Compra;
import br.com.letscode.projetopa.compra.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<RespostaCompraDTO> cadastrarCompra (@RequestBody RequisicaoCompraDTO requisicaoCompra) {
        return ResponseEntity.ok(compraService.cadastraCompra(requisicaoCompra));
    }

    @GetMapping
    public ResponseEntity<Page<RespostaCompraDTO>> listarCompras (@RequestParam @Nullable String cpf, Pageable pageable) {
        return ResponseEntity.ok(compraService.listaCompras(cpf, pageable));
    }

}
