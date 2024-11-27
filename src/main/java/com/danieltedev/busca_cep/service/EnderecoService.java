package com.danieltedev.busca_cep.service;

import org.springframework.stereotype.Service;

import com.danieltedev.busca_cep.client.CepClient;
import com.danieltedev.busca_cep.client.response.CepResponse;
import com.danieltedev.busca_cep.entity.EnderecoEntity;
import com.danieltedev.busca_cep.repository.EnderecoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final CepClient cepClient;
    private final EnderecoRepository cepRepository;

    public CepResponse consultarCep(String cep) {
        CepResponse cepResponse = cepClient.getCep(cep);
        EnderecoEntity cepEntity = new EnderecoEntity(cepResponse);
        cepRepository.save(cepEntity);
        return cepResponse;
    }
}
