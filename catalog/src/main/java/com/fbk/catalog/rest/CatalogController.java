package com.fbk.catalog.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.fbk.catalog.domain.Product;
import com.fbk.catalog.service.ProductService;

@RestController()
@RequestMapping("/api")
@CrossOrigin
public class CatalogController {
    
    @Autowired
    private ProductService service;

    @GetMapping("/info")
    public Map<String, String> apiInfo() {
        Map<String, String> apiInfo = new HashMap<>();
        
        apiInfo.put("GET /api/products", "Ottieni l'elenco dei prodotti disponibili");
        apiInfo.put("GET /api/product/{id}", "Ottieni un prodotto specifico per ID");
        apiInfo.put("GET /api/products/category/{category}", "Trova prodotti per categoria");
        apiInfo.put("POST /api/addProduct", "Aggiungi un nuovo prodotto");
        apiInfo.put("POST /api/addProducts", "Aggiungi una lista di prodotti");
        apiInfo.put("PUT /api/products/{id}/availability/{amount}", "Modifica la quantità disponibile di un prodotto");

        return apiInfo;
    }


    @GetMapping("/products")
    public @ResponseBody List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/product/{id}")
    public @ResponseBody Product getProduct(@PathVariable String id) {
        return service.getProduct(id);
    }

    @GetMapping("/products/category/{category}")
    public @ResponseBody Page<Product> findProducts(@PathVariable String category, Pageable page) {
        return service.findProducts(category, page);
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    @PutMapping("/products/{id}/availability/{amount}")
    public @ResponseBody Product changeQuantity(@PathVariable String id, @PathVariable int amount) {
        return service.changeQuantity(id, amount);
    }
}
