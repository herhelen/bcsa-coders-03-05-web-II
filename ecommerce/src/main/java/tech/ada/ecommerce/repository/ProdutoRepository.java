package tech.ada.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.ada.ecommerce.model.Produto;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.nome ILIKE %:nome% ORDER BY p.nome")
    List<Produto> findByNomeCustom(@Param("nome") String nome);

    @Query("UPDATE Produto p SET p.qtdeEstoque = :qtd WHERE p.id = :id")
    void atualizarEstoque(@Param("qtd") int qtd, @Param("id") Long id);


    Produto findProdutoBySku(String sku);

    List<Produto> findProdutosByNomeIsLikeIgnoreCase(String nome);

    List<Produto> findProdutosByPrecoBetween(BigDecimal precoMenor, BigDecimal precoMaior);

    @Override
    Page<Produto> findAll(Pageable pageable); // paginacao no spring boot
}
