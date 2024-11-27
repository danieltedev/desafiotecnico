package com.danieltedev.busca_cep.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.danieltedev.busca_cep.client.response.CepResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder(toBuilder=true)
@Table(name="enderecos")
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cep;
    private String logradouro;
    @CreatedDate
    private LocalDateTime createDate;

    public EnderecoEntity(CepResponse cepResponse) {
        this.cep = cepResponse.getCep();
        this.logradouro = cepResponse.getLogradouro();
    }

}
