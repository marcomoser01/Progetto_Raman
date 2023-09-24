package com.fbk.rating.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fbk.rating.domain.Product;

@Service
public class ProductService {

	private String endpoint = "http://localhost:8080";
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public Product getProduct(String product_id) {
		return restTemplate.getForObject(endpoint + "/api/products/{productId}", Product.class, product_id);
	}
}
