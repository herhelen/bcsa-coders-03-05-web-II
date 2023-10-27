package tech.ada.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tech.ada.ecommerce.dto.ClienteDTO;
import tech.ada.ecommerce.model.Cliente;
import tech.ada.ecommerce.repository.ClienteQDSLRepository;
import tech.ada.ecommerce.repository.ClienteRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    //@Autowired
    ClienteRepository clienteRepository;
    ClienteQDSLRepository clienteQDSLRepository;

    public ClienteService(ClienteRepository clienteRepository, ClienteQDSLRepository clienteQDSLRepository) {
        this.clienteRepository = clienteRepository;
        this.clienteQDSLRepository = clienteQDSLRepository;
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

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        try {
            DateFormat dft = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNascimento = dft.parse(clienteDTO.getDataNascimento());

            Cliente cliente;
            if(clienteDTO.getId() != null) {
                cliente = new Cliente(
                    clienteDTO.getId(),
                    clienteDTO.getNomeCompleto(),
                    dataNascimento,
                    clienteDTO.getCpf(),
                    clienteDTO.getEmail(),
                    clienteDTO.getSenha(),
                    clienteDTO.isAtivo());
            } else {
                cliente = new Cliente(
                        clienteDTO.getNomeCompleto(),
                        dataNascimento,
                        clienteDTO.getCpf(),
                        clienteDTO.getEmail(),
                        clienteDTO.getSenha(),
                        clienteDTO.isAtivo());
            }

            Cliente savedCliente = this.clienteRepository.save(cliente);

            return criarClienteDTO(savedCliente);

        } catch(ParseException exception) {
            return null;
        }
    }

    public ClienteDTO buscarPorId(Long id) {
        Optional<Cliente> opCliente = this.clienteRepository.findById(id);
        Cliente cliente = opCliente.orElseThrow(() -> new RuntimeException("Não existe cliente com id " + id));

        return criarClienteDTO(cliente);
    }

    public List<Cliente> buscarPorNome(String nome) {
//        return this.clienteRepository.findByNomeCompleto(nome);
        return this.clienteRepository.findByNomeCompletoLike("%" + nome + "%");
    }

    public List<Cliente> buscarPorNomeCustom(String nome) {
        return this.clienteRepository.findByNomeCompletoCustom(nome);
    }

    public void ativarDesativarCliente(boolean ativo, Long id) {
        this.clienteRepository.ativarUsuario(ativo, id);
    }

    public List<Cliente> buscarClientesAtivos() {
        return this.clienteRepository.findByAtivo(true);
    }


    private ClienteDTO criarClienteDTO(Cliente cliente) {
        DateFormat dft = new SimpleDateFormat("dd/MM/yyyy");
        String sDataNascimento = dft.format(cliente.getDataNascimento());

        return ClienteDTO.builder()
                .id(cliente.getId())
                .nomeCompleto(cliente.getNomeCompleto())
                .cpf(cliente.getCpf())
                .dataNascimento(sDataNascimento)
                .email(cliente.getEmail())
                .senha(cliente.getSenha())
                .ativo(cliente.isAtivo()).build();
    }

    public void excluirCliente(Long idCliente) {
        this.clienteRepository.deleteById(idCliente);
    }


    // QDSL
    public List<Cliente> qdslFindAll() {
        return this.clienteQDSLRepository.findAll();
    }

    public Cliente qdslFindById(Long id) {
        return this.clienteQDSLRepository.findById(id);
    }

    public List<Cliente> qdslFindByNome(String nome) {
        return this.clienteQDSLRepository.findByNome(nome);
    }

}
