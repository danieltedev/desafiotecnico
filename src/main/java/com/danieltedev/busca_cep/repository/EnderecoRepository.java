package com.danieltedev.busca_cep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danieltedev.busca_cep.entity.EnderecoEntity;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    
}
