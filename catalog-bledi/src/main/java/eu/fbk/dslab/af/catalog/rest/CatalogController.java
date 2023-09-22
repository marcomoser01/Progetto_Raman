package eu.fbk.dslab.af.catalog.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import eu.fbk.dslab.af.catalog.domain.Product;
import eu.fbk.dslab.af.catalog.domain.Rate;
// import eu.fbk.dslab.af.catalog.service.RatingServices;
import eu.fbk.dslab.af.catalog.service.ProductService;

@RestController()
@RequestMapping("/api")
public class CatalogController {
    
    @Autowired
    private ProductService service;
    // private RatingServices service2;
    
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

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @PutMapping("/products/{id}/availability/{amount}")
    public @ResponseBody Product changeQuantity(@PathVariable String id, @PathVariable int amount) {
        return service.changeQuantity(id, amount);
    }

// // -------------------------------------
//     @PostMapping("/ratings/{productId}/{userId}")
//     public Rate GetRating(@RequestBody Rate rate) {
//         return service2.findRating(rate);
//     }

    // @GetMapping("/ratings/{productId}")
    // public @ResponseBody Page<Product> findProducts2(@PathVariable String category, Pageable page) {
    //     return service2.findProducts(category, page);
    // }

    // @GetMapping("/popular")
    // public @ResponseBody Page<Product> findProducts3(@PathVariable String category, Pageable page) {
    //     return service2.findProducts(category, page);
    // }
}
