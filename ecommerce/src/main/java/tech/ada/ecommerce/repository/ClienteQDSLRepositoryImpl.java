package tech.ada.ecommerce.repository;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import tech.ada.ecommerce.model.Cliente;
import tech.ada.ecommerce.model.QCliente;

import java.util.List;

@Repository
public class ClienteQDSLRepositoryImpl implements ClienteQDSLRepository {

    private EntityManager entityManager; // Spring Data
    private JPAQueryFactory jpaQueryFactory;
    private QCliente cliente;

    public ClienteQDSLRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
        this.cliente = QCliente.cliente;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = this.jpaQueryFactory.selectFrom(this.cliente).fetch();
        return clientes;
    }

    @Override
    public Cliente findById(Long id) {
        return this.jpaQueryFactory.selectFrom(this.cliente)
                .where(this.cliente.id.eq(id))
                .fetchFirst();
    }

    @Override
    public List<Cliente> findByNome(String nome) {
        return jpaQueryFactory.selectFrom(this.cliente)
                .where(this.cliente.nomeCompleto.like("%" + nome + "%"))
                .orderBy(this.cliente.nomeCompleto.desc())
                .fetch();
    }
}
