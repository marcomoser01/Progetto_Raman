package com.fbk.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fbk.catalog.domain.Product;
import com.fbk.catalog.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repo;

    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        return repo.saveAll(products);
    }

    public List<Product> getProducts() {
        return repo.findAll();
    }

    public Page<Product> getProducts(Pageable page) {
        return repo.findAll(page);
    }

    public Product getProduct(String id) {
        return repo.findById(Integer.parseInt(id)).orElse(null);
    }

    public Page<Product> findProducts(String category, Pageable page) {
        return repo.findByCategory(category,page);
    }

    public Product changeQuantity(String id, int amount) {

        Product p = getProduct(id);
        if (p != null) {
            p.setQuantity(p.getQuantity() - amount);
            p = saveProduct(p);
        }
        return p;
    }

}
