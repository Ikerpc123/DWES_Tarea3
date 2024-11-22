package com.ikerpc123.tarea3dwesiker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tarea3dwesikerApplication {
	
	@Bean
	public Principal applicationStartupRunner() {
		return new Principal();
		
	}

	public static void main(String[] args) {
		SpringApplication.run(Tarea3dwesikerApplication.class, args);
	}

}
