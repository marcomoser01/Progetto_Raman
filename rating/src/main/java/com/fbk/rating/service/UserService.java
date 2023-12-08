package com.fbk.rating.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fbk.rating.domain.User;

//questa classe è un componente di servizio.
@Service
public class UserService {
	//Specifica l'URL di base del servizio remoto a cui il servizio intende accedere
    private String endpoint = "http://gateway:9999/users";
	//  Crea un'istanza di RestTemplate per effettuare richieste HTTP
	private RestTemplate restTemplate = new RestTemplate();
	// Questo metodo effettua una richiesta HTTP GET al endpoint
	public User getUser(String user_Id) {
		// l'istanza  restTemplate  effettua una richiesta GET al endpoint con l'aggiunta dell'ID utente.
		//risposta viene mappata in un oggetto di tipo User.
		return restTemplate.getForObject(endpoint + "/api/user/{userId}", User.class, user_Id);
	}
	
}
