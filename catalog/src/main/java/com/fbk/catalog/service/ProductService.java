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
    // utilizzata per l'iniezione delle dipendenze in Spring
    //  iniettare un'istanza di ProductRepository in questa classe quando viene creata
    @Autowired
    private ProductRepository repo;

    // Questo metodo salva un singolo prodotto nel database
    //oggetto di tipo product
    public Product saveProduct(Product product) {
        // Restituisce l'oggetto Product
        return repo.save(product);
    }
    //restituisce lista di oggetti di prodotti 
    // salva una lista di prodotti nel database
                                     //input lista di oggetti Product
    public List<Product> saveProducts(List<Product> products) {
        // Restituisce la lista di prodotti salvati.
        return repo.saveAll(products);
    }
    // Questo metodo restituisce tutti i prodotti presenti nel database.
    public List<Product> getProducts() {
        // restituisce una lista di tutte le entità presenti nel repository.
        return repo.findAll();
    }
    // Questo metodo restituisce una pagina(lista) di prodotti basata su una richiesta paginata (Pageable
    public Page<Product> getProducts(Pageable page) {
        //ottiene risultati paginati
        return repo.findAll(page);
    }
    //oggetto product input id 
    public Product getProduct(String id) {
        //Questo metodo restituisce un oggetto Product cercandolo nel repository tramite l'ID specificato.
        //findById (del repoaitory ProductRepositor) cerca con ID(convertito in intero) nel DB se non viene trovato restituisce NULL
        return repo.findById(Integer.parseInt(id)).orElse(null);
    }
    // restituisce una pagina di oggetti Product
    public Page<Product> findProducts(String category, Pageable page) {
        // Restituisce una pagina di oggetti Product corrispondenti alla categoria fornita.
        return repo.findByCategory(category,page);
    }
    // Questo metodo cambia la quantità di un prodotto specificato nell'ID, riducendola di una determinata quantità (amount).
    public Product changeQuantity(String id, int amount) {
        // Utilizza il metodo getProduct per ottenere l'oggetto Product corrispondente all'ID specificato.
        Product p = getProduct(id);
        // Verifica se l'oggetto Product è stato trovato
        if (p != null) {
            // Se l'oggetto esiste, la quantità del prodotto viene aggiornata.
            // Riduce la quantità del prodotto di amount.e la aggiorna nel DB
            p.setQuantity(p.getQuantity() - amount);
            p = saveProduct(p);
        }
        // Restituisce l'oggetto Product aggiornato.
        return p;
    }

}
