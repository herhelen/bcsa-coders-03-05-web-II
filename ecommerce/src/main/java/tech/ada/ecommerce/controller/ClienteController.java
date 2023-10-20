package tech.ada.ecommerce.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<Cliente>> getClienteByNome(@RequestParam("nome") String nome) {
        return new ResponseEntity<>(this.clienteService.buscarPorNomeCustom(nome), HttpStatus.OK);
    }

    @GetMapping("/todos")
    public Page<Cliente> findAllClientes(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            Pageable pageable) {
        return this.clienteService.buscarTodosClientesCustom(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long idCliente) {
        return new ResponseEntity<>(this.clienteService.buscarPorId(idCliente), HttpStatus.OK);
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente(@RequestBody Cliente cliente) {
//    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = this.clienteService.criarCliente(cliente);
        return savedCliente;
    }
}
