package com.fbk.purchase.service;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fbk.purchase.domain.Product;

@Service
public class ProductService {

	private String endpoint = "http://gateway:9999/catalog";
	// RestTemplate per fare una richiesta HTTP GET a un'API esterna, in particolare all'indirizzo endpint
	private RestTemplate restTemplate = new RestTemplate();
	// prende come parametro l'ID del prodotto (productId) che si desidera recuperare.
	public Product getProduct(String productId) {
		// concatena delle stringhe url
		String api = endpoint + "/api/product/" + productId;
		// Utilizza RestTemplate per fare una richiesta HTTP GET all'URL ottenuto e ricevere info sul prodotto
		return restTemplate.getForObject(api, Product.class);
	}
	// effettuare una chiamata HTTP PUT a un'API esterna per "prenotare" una certa quantità di disponibilità per un dato prodotto
	public Product bookAvailability(String productId, int quantity) {
		//Utilizza RestTemplate per eseguire una richiesta HTTP PUT all'API specificata e ottiene la risposta come un oggetto Product
		// aggiornare la disponibilità di un prodotto specifico
		//exchange specificare i parametri 
		return restTemplate.exchange(
				endpoint + "/api/products/{productId}/availability/{quantity}", 
				HttpMethod.PUT, 
				//Non sono forniti parametri del corpo della richiesta
				null, 
				// Specifica che ci si aspetta una risposta di tipo Product dalla chiamata PUT.
				Product.class, 
				productId,
				// - quantita negativa 
				-quantity).getBody();
	}
}
