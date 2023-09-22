package eu.fbk.dslab.af.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import eu.fbk.dslab.af.order.domain.ProductObject;

@Service
public class CatalogService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory factory;

    public ProductObject getProductFromCatalog(String productId) {
        CircuitBreaker cb = factory.create("circuitbreaker");
        return cb.run(() -> {
            ProductObject product = restTemplate.getForObject(
                "http://catalog/api/products/" + productId, 
                ProductObject.class);
            return product;
        }
        , err -> {
            System.err.println("Error di circuit breaker: " + err.getMessage());
            return null;
        });
    }

    public void changeProductAvailability(String productId, Integer quantity) {
        restTemplate.put("http://catalog/api/products/" + productId+"/availability/"+quantity, null);
    }
}
