package com.danieltedev.busca_cep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BuscaCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuscaCepApplication.class, args);
	}

}
