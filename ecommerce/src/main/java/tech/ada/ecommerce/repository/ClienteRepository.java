package tech.ada.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.ada.ecommerce.model.Cliente;

import java.util.Date;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeCompleto(String nome);

    List<Cliente> findByNomeCompletoLike(String nome);

    List<Cliente> findByDataNascimentoBetween(Date data1, Date data2);

    @Query("SELECT c FROM Cliente c WHERE c.nomeCompleto ILIKE %:nome% ORDER BY c.nomeCompleto")
    List<Cliente> findByNomeCompletoCustom(@Param("nome") String nome);

    @Query(value = "SELECT * FROM cliente ORDER BY NOME_COMPLETO", nativeQuery = true)
    List<Cliente> findAllCustom();

    @Query(value = "SELECT * FROM cliente ORDER BY NOME_COMPLETO", nativeQuery = true)
    Page<Cliente> findAllCustom(Pageable pageable);
}
