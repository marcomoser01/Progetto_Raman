package com.fbk.catalog.rest;

import java.util.List;

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
