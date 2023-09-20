package eu.fbk.dslab.af.catalog.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import eu.fbk.dslab.af.catalog.domain.Product;
import eu.fbk.dslab.af.catalog.service.ProductService;

@RestController()
@RequestMapping("/api")
public class CatalogController {
    
    @Autowired
    private ProductService service;
    
    @GetMapping("/products")
    public @ResponseBody Page<Product> getProducts(Pageable page) {
        return service.getProducts(page);
    }

    @GetMapping("/products/{id}")
    public @ResponseBody Product getProduct(@PathVariable String id) {
        return service.getProduct(id);
    }

    @GetMapping("/products/category/{category}")
    public @ResponseBody Page<Product> findProducts(@PathVariable String category, Pageable page) {
        return service.findProducts(category, page);
    }

    @PostMapping("/products")
    public @ResponseBody Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @PutMapping("/products/{id}/availability/{amount}")
    public @ResponseBody Product changeQuantity(@PathVariable String id, @PathVariable int amount) {
        return service.changeQuantity(id, amount);
    }
}
