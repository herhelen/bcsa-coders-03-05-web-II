package tech.ada.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.ecommerce.model.Produto;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findProdutoBySku(String sku);

    List<Produto> findProdutosByPrecoBetween(BigDecimal precoMenor, BigDecimal precoMaior);

    @Override
    Page<Produto> findAll(Pageable pageable); // paginacao no spring boot
}
