package com.fbk.catalog.repository;

import java.util.Optional;
import javax.swing.Spring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.catalog.domain.Product;

// ProductRepository fornisce metodi dichiarativi per eseguire operazioni comuni su entità Product attraverso Spring Data JPA
// per interagire con il database per la gestione delle operazioni CRUD 
//gestisce l'accesso ai dati per l'entità Product.
public interface ProductRepository extends JpaRepository<Product, Integer>{
    // uesto è un metodo di query derivato di Spring Data JPA. Cerca i prodotti per categoria e restituisce una pagina (Page<Product>) 
    // di risultati.
    //pagable specifica informazioni sulla paginazione
    Page<Product> findByCategory(String category, Pageable page);
    // Questo metodo cerca un prodotto per ID e restituisce un oggetto 
    Optional<Product> findById(Integer id);


}
