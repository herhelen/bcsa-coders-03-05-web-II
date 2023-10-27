package tech.ada.ecommerce.service;

import org.springframework.stereotype.Service;
import tech.ada.ecommerce.model.Compra;
import tech.ada.ecommerce.model.ItemProduto;
import tech.ada.ecommerce.repository.CompraRepository;
import tech.ada.ecommerce.repository.ItemProdutoRepository;

import java.util.Optional;

@Service
public class CompraService {

    private CompraRepository compraRepository;
    private ItemProdutoRepository itemProdutoRepository;

    public CompraService(CompraRepository compraRepository, ItemProdutoRepository itemProdutoRepository) {
        this.compraRepository = compraRepository;
        this.itemProdutoRepository = itemProdutoRepository;
    }

    public Compra buscarCompra(Long id) {
        Optional<Compra> compra = this.compraRepository.findById(id);
        return compra.orElseThrow(() -> new RuntimeException("Compra não encontrada."));
    }

    public void salvarCompra(Compra compra, ItemProduto itemProduto) {
        // FetchType.EAGER
//        ItemProduto novo = new ItemProduto();
//        compra.getItens().add(novo);
//        this.compraRepository.save(compra);

        // FetchType.Lazy
        Compra savedCompra = this.compraRepository.save(compra);
        itemProduto.setCompra(savedCompra);
        this.itemProdutoRepository.save(itemProduto);

    }

}
