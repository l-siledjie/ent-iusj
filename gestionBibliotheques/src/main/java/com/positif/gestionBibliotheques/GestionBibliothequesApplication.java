package com.positif.gestionBibliotheques;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestionBibliothequesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionBibliothequesApplication.class, args);
	}

}




