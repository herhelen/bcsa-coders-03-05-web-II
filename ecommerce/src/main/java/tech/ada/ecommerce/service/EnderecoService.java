package tech.ada.ecommerce.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tech.ada.ecommerce.dto.CEP;
import tech.ada.ecommerce.model.Endereco;
import tech.ada.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    // Implementação usando RestTemplate
//    public CEP buscaPorCep(String cep) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://viacep.com.br/ws/";
//        ResponseEntity<CEP> response = restTemplate.getForEntity(url + cep + "/json", CEP.class);
//
//        return response.getBody();
//    }

    public CEP buscaPorCep(String cep) {
        String url = "https://viacep.com.br/ws/";

        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri(cep + "/json") // junta com baseUrl
                .retrieve() // fazer a chamada e retorna response
                .bodyToMono(CEP.class) // mono => sincrono
                .block(); // classe de resposta
    }

    public Endereco saveEndereco(Endereco endereco) {
        CEP cep = buscaPorCep(endereco.getCep());
        endereco.setUf(cep.getUf());
        endereco.setCidade(cep.getLocalidade());
        endereco.setBairro(cep.getBairro());
        endereco.setLogradouro(cep.getLogradouro());
        return this.enderecoRepository.save(endereco);
    }

}
