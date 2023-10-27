package tech.ada.ecommerce.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.ecommerce.model.Produto;
import tech.ada.ecommerce.service.ProdutoService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // sku
    @GetMapping("/sku")
    public ResponseEntity<Produto> getProdutoBySku(@RequestParam("sku") String sku) {
        return new ResponseEntity<>(this.produtoService.buscarProdutoPorSku(sku), HttpStatus.OK);
    }

    // nome
    @GetMapping("/nome")
    public ResponseEntity<List<Produto>> getProdutosByNome(@RequestParam("nome") String nome) {
        return new ResponseEntity<>(this.produtoService.buscarProdutosPorNome(nome), HttpStatus.OK);
    }

    // entre precos
    @GetMapping("/precos")
    public ResponseEntity<List<Produto>> getProdutosByPrecos(
            @RequestParam BigDecimal precoMenor,
            @RequestParam BigDecimal precoMaior) {
        return new ResponseEntity<>(
                this.produtoService.buscarProdutosPorPrecos(precoMenor, precoMaior),
                HttpStatus.OK);
    }

    // todos paginados
    @GetMapping("")
    public Page<Produto> findAllProdutos() {
        return this.produtoService.buscarTodosProdutos();
    }
}
