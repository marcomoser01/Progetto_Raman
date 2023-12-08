package com.fbk.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//Indica che la classe contrassegnata è una classe di configurazione principale di un'applicazione Spring Boot.
//Abilita le annotazioni,Configura automaticamente l'applicazione Spring.
@SpringBootApplication
//Configura automaticamente l'applicazione Spring,registrazione/deregistrazione dei microservizi.
@EnableEurekaServer
public class RegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistryApplication.class, args);
	}

}
