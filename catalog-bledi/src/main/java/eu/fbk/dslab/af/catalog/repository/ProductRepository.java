package eu.fbk.dslab.af.catalog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import eu.fbk.dslab.af.catalog.domain.Product;


public interface ProductRepository extends JpaRepository<Product, String>{

    Page<Product> findByCategory(String category, Pageable page);
    
    Optional<Product> findById(String id);


}
