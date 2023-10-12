package tech.ada.ecommerce.service;

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
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> opCliente = clienteRepository.findById(id);
        return opCliente.orElseThrow(() -> new RuntimeException("Não existe cliente com id " + id));
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeCompleto(nome);
    }

    public List<Cliente> buscarPorNomeCustom(String nome) {
        return clienteRepository.findByNomeCompletoCustom(nome);
    }
}
