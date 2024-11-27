package com.danieltedev.busca_cep.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danieltedev.busca_cep.client.response.CepResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CepClient {
    
    private final RestTemplate restTemplate;

    @Value("${app.api.cep.url}")
    private String url;

    public CepResponse getCep(String cep) {
        return restTemplate.getForObject(url + "/cep/{cep}", CepResponse.class, cep);
    }
}
