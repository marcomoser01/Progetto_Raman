package com.fbk.purchase.service;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fbk.purchase.domain.Product;

@Service
public class ProductService {

	private String endpoint = "http://localhost:8080";
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public Product getProduct(String productId) {
		return restTemplate.getForObject(endpoint + "/api/products/{productId}", Product.class, productId);
	}
	
	public Product bookAvailability(String productId, int quantity) {
		return restTemplate.exchange(
				endpoint + "/api/products/{productId}/availability/{quantity}", 
				HttpMethod.PUT, 
				null, 
				Product.class, 
				productId, 
				-quantity).getBody();
	}
}
