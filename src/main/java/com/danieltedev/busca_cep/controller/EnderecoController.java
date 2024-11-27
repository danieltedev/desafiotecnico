package com.danieltedev.busca_cep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danieltedev.busca_cep.client.response.CepResponse;
import com.danieltedev.busca_cep.service.EnderecoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ceps")
@RequiredArgsConstructor
public class EnderecoController {
    
    private final EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public CepResponse consultarCep(@PathVariable String cep) {
        return enderecoService.consultarCep(cep);
    }
}
