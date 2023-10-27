package tech.ada.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.ecommerce.dto.CEP;
import tech.ada.ecommerce.model.Endereco;
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

    @RequestMapping(value = "", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseEntity<Endereco> saveEndereco(@RequestBody Endereco endereco) {
        try {
            Endereco savedEndereco = enderecoService.saveEndereco(endereco);
            if (savedEndereco != null)
                return new ResponseEntity<>(savedEndereco, HttpStatus.CREATED);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
