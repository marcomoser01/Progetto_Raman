package eu.fbk.dslab.af.order.service;

import org.springframework.stereotype.Component;

import eu.fbk.dslab.af.order.domain.ProductObject;

@Component
public class FallbackCatalogService implements OpenFeignCatalogService{

    @Override
    public ProductObject getProductFromCatalog(String productId) {
        return null;
    }

    @Override
    public void changeProductAvailability(String productId, Integer quantity) {
        return;
    }
    
}
