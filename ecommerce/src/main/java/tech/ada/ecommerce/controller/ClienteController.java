package tech.ada.ecommerce.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.ecommerce.dto.ClienteDTO;
import tech.ada.ecommerce.model.Cliente;
import tech.ada.ecommerce.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("")
//    @RequestMapping(method = RequestMethod.GET)
    public List<Cliente> getClientes() {
        return this.clienteService.buscarTodosClientes();
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Cliente>> getClientesByNome(@RequestParam("nome") String nome) {
        return new ResponseEntity<>(this.clienteService.buscarPorNome(nome), HttpStatus.OK);
    }

    @GetMapping("/todos")
    public Page<Cliente> findAllClientes(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            Pageable pageable) {
        return this.clienteService.buscarTodosClientesCustom(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable("id") Long idCliente) {
        return new ResponseEntity<>(this.clienteService.buscarPorId(idCliente), HttpStatus.OK);
    }


//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Cliente saveCliente(@RequestBody Cliente cliente) {
    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<ClienteDTO> saveCliente(@RequestBody ClienteDTO cliente) {
//        Cliente savedCliente = this.clienteService.criarCliente(cliente);
//        return savedCliente;
        try {
            ClienteDTO savedCliente = this.clienteService.criarCliente(cliente);
            if(savedCliente != null)
                return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable("id") Long idCliente) {
        this.clienteService.excluirCliente(idCliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> ativarDesativarCliente(@PathVariable("id") Long id, @RequestParam("ativo") boolean ativo) {
        this.clienteService.ativarDesativarCliente(ativo, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/ativos")
    public List<Cliente> buscarClientesAtivos() {
        return this.clienteService.buscarClientesAtivos();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ClienteDTO> atualizarCliente(
//            @PathVariable("id") Long idCliente,
//            @RequestBody ClienteDTO clienteDTO
//    ) {
//        try {
//            ClienteDTO savedCliente = this.clienteService.criarCliente(clienteDTO);
//            if(savedCliente != null)
//                return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        } catch (Exception exception) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}

