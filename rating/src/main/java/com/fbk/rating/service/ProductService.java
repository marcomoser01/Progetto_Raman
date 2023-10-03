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

	private String endpoint = "http://localhost:8080";

	private RestTemplate restTemplate = new RestTemplate();

	public List<Product> getAllProducts() {
		ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
				endpoint + "/api/products",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Product>>() {
				});

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity.getBody();
		} else {
			// Gestire il caso in cui la richiesta non ha avuto successo
			return Collections.emptyList(); // O un altro valore di default appropriato
		}
	}

	public Product getProduct(String product_id) {
		return restTemplate.getForObject(endpoint + "/api/products/{productId}", Product.class, product_id);
	}
}
