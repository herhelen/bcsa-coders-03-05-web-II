package tech.ada.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tech.ada.ecommerce.model.Cliente;
import tech.ada.ecommerce.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    //@Autowired
    ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> buscarTodosClientes() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        return clientes;
    }

    public Page<Cliente> buscarTodosClientesCustom(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "NOME_COMPLETO");
        Page<Cliente> clientes = this.clienteRepository.findAllCustom(pageable);

        return clientes;
    }

    public Cliente criarCliente(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> opCliente = this.clienteRepository.findById(id);
        return opCliente.orElseThrow(() -> new RuntimeException("Não existe cliente com id " + id));
    }

    public List<Cliente> buscarPorNome(String nome) {
        return this.clienteRepository.findByNomeCompleto(nome);
    }

    public List<Cliente> buscarPorNomeCustom(String nome) {
        return this.clienteRepository.findByNomeCompletoCustom(nome);
    }
}
