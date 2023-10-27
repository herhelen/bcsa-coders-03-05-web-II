package tech.ada.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.ecommerce.dto.CEP;
import tech.ada.ecommerce.service.EnderecoService;

@RestController
@RequestMapping("/api/v1/endereco")
public class EndrecoController {

    private EnderecoService enderecoService;

    public EndrecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<CEP>  getByCep(@PathVariable("cep") String cep) {
        return new ResponseEntity<>(this.enderecoService.buscaPorCep(cep), HttpStatus.OK);
    }
}
