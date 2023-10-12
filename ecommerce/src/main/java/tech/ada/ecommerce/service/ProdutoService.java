package tech.ada.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.ada.ecommerce.model.Produto;
import tech.ada.ecommerce.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> buscarProdutosPorPrecos(BigDecimal precoMenor, BigDecimal precoMaior) {
        return this.produtoRepository.findProdutosByPrecoBetween(precoMenor, precoMaior);
    }

    public Page<Produto> buscarTodosProdutos() {
        // numeração de Page é igual ao a indexação de vetor, começa no 0
        int page = 0;
        int total = 100;
        Pageable pageable = PageRequest.of(page, total);
        return this.produtoRepository.findAll(pageable);
    }
}
