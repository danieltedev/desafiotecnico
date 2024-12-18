package com.danieltedev.busca_cep.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CepResponse {
    private Long cep;
    private String logradouro;
}
