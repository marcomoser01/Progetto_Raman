package com.fbk.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

// Questa annotazione è una convenzione di Spring Boot 
@SpringBootApplication
// Un server di configurazione consente agli altri servizi o microservizi di recuperare la loro configurazione da un'origine centralizzata, di solito archiviata in un repository di configurazione remoto come Git
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
