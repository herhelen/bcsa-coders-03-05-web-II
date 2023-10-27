package tech.ada.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.ecommerce.model.Cliente;
import tech.ada.ecommerce.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/qdsl")
public class ClienteQDSLController {

    ClienteService clienteService;

    public ClienteQDSLController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("")
    public List<Cliente> getClientes() {
        return this.clienteService.qdslFindAll();
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Cliente>> getClientesByNome(@RequestParam("nome") String nome) {
        return new ResponseEntity<>(this.clienteService.qdslFindByNome(nome), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long idCliente) {
        return new ResponseEntity<>(this.clienteService.qdslFindById(idCliente), HttpStatus.OK);
    }

}

