package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CassandraApplication {
	private final static Logger log = LoggerFactory.getLogger(CassandraApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CassandraApplication.class, args);
	}
	@Bean
	  public CommandLineRunner clr(PersonajeRepository perRepository) {
	    return args -> {
	      perRepository.deleteAll();
	      
	      Personaje juan = new Personaje((long) 1, "Juan Cuesta", "Desengaño 21, 2ºA", "Hacer Maquetas", "Profesor", true);
	      Personaje paloma = new Personaje((long) 2, "Paloma Cuesta", "Desengaño 21, 2ºA", "Cotillear", "Ama de casa", true);
	      
	      Personaje savedJuan = perRepository.save(juan);
	      Personaje savedPaloma = perRepository.save(paloma);
	  };
}
}