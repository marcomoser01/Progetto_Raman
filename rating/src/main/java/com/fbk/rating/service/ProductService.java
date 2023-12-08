package com.fbk.rating.service;

import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fbk.rating.domain.Product;

@Service
public class ProductService {
	//Specifica l'URL di base del servizio remoto a cui il servizio intende accedere
    private String endpoint = "http://gateway:9999/catalog";
	//  Crea un'istanza di RestTemplate per effettuare richieste HTTP
	private RestTemplate restTemplate = new RestTemplate();
	// fa una GET per ottenere l'elenco completo dei prodotti.
	public List<Product> getAllProducts() {
		// restTemplate.exchange(...) fa una richiesta HTTP GET con la possibilità di ottenere una risposta tipizzata (List<Product>) .
		ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
				endpoint + "/api/products",
				HttpMethod.GET,
				null,
				//Consente di ottenere una risposta tipizzata per gestire il corpo della risposta come una lista di oggetti Product.
				new ParameterizedTypeReference<List<Product>>() {
				});
		// Se la richiesta ha successo, restituisce la lista di prodotti; altrimenti, restituisce una lista vuota o un altro valore di default.
		//verifica se lo stato della risposta HTTP è di successo nel range 2xx.
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			//Se lo stato della risposta è nel range 2xx, allora questa parte del codice restituirà il corpo della risposta
			return responseEntity.getBody();
		} else {
			// In questo caso, viene restituito un elenco vuoto 
			return Collections.emptyList(); 
		}
	}
	//Questo metodo effettua una richiesta HTTP GET al servizio remoto per ottenere informazioni su un prodotto specificato dall'ID.
	public Product getProduct(String product_id) {
		// l'istanza  restTemplate  effettua una richiesta GET al endpoint con l'aggiunta dell'ID utente.
		//risposta viene mappata in un oggetto di tipo Product.
		return restTemplate.getForObject(endpoint + "/api/product/{productId}", Product.class, product_id);
	}
}
