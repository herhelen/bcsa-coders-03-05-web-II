package tech.ada.ecommerce.repository;

import org.springframework.stereotype.Repository;
import tech.ada.ecommerce.model.Cliente;

import java.util.List;

public interface ClienteQDSLRepository {

    List<Cliente> findAll();
    Cliente findById(Long id);
    List<Cliente> findByNome(String nome);
}
