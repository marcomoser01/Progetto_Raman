package eu.fbk.dslab.af.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import eu.fbk.dslab.af.order.domain.ProductObject;

@FeignClient(value = "catalog", fallback = FallbackCatalogService.class)
public interface OpenFeignCatalogService {
    
    @GetMapping("/api/products/{productId}")
    public ProductObject getProductFromCatalog(@PathVariable String productId);
    @PutMapping("/api/products/{productId}/availability/{quantity}")
    public void changeProductAvailability(@PathVariable String productId, @PathVariable Integer quantity);
}
