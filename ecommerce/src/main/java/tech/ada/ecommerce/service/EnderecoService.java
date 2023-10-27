package tech.ada.ecommerce.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import tech.ada.ecommerce.dto.CEP;

@Service
public class EnderecoService {

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

}
