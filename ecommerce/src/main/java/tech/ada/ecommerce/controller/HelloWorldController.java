package tech.ada.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ada.ecommerce.model.Cliente;
import tech.ada.ecommerce.model.Produto;
import tech.ada.ecommerce.repository.ClienteRepository;
import tech.ada.ecommerce.service.ClienteService;
import tech.ada.ecommerce.service.ProdutoService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("Hello World Spring Boot!");
        return "<strong>Hello World Spring Boot!</strong>";
    }

    @GetMapping("/produto")
    @ResponseBody
    public List<Produto> buscarProdutosPorPrecos(
            @RequestParam BigDecimal precoMenor,
            @RequestParam BigDecimal precoMaior) {
//        return "precoMenor: " + precoMenor + " precoMaior: " + precoMaior;
        return produtoService.buscarProdutosPorPrecos(precoMenor, precoMaior);
    }

    @GetMapping("/cliente/{nome}")
    public List<Cliente> buscarClientePorNome(@PathVariable String nome) {
        return clienteService.buscarPorNomeCustom(nome);
    }

}
