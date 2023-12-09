package com.fbk.catalog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.catalog.domain.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{

    Page<Product> findByCategory(String category, Pageable page);
    
    Optional<Product> findById(Integer id);

}
