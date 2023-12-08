package com.fbk.catalog.rest;

import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.fbk.catalog.domain.Product;
import com.fbk.catalog.service.ProductService;

//gestire le richieste HTTP e restituire le risposte come dati JSON (o XML) in base ai casi d'uso.
@RestController()
//Specifica il prefisso per tutte le richieste mappate 
@RequestMapping("/api")
//specificare quali domini possono accedere alle risorse fornite dal controller.
@CrossOrigin
public class CatalogController {
    //utilizzata per l'iniezione di dipendenze automatica
    //riduce configurazioni manuali
    @Autowired
    //attributo
    private ProductService service;
    //gestirà le richieste HTTP GET indirizzate a "/api/info"
    @GetMapping("/info")
    public String apiInfo() {
        // costruire un oggetto JSON (apiInfo) che rappresenta informazioni sull'API, 
        // fornendo descrizioni per diverse operazioni. Infine, viene restituito un oggetto JSON in formato stringa.
        JSONObject apiInfo = new JSONObject();
        //viene convertito in una stringa JSON utilizzando il metodo toString().
        apiInfo.put("GET /api/products", "Ottieni l'elenco dei prodotti disponibili");
        apiInfo.put("GET /api/product/{id}", "Ottieni un prodotto specifico per ID");
        apiInfo.put("GET /api/products/category/{category}", "Trova prodotti per categoria");
        apiInfo.put("POST /api/addProduct", "Aggiungi un nuovo prodotto");
        apiInfo.put("POST /api/addProducts", "Aggiungi una lista di prodotti");
        apiInfo.put("PUT /api/products/{id}/availability/{amount}", "Modifica la quantità disponibile di un prodotto");
        //La stringa JSON risultante contiene le informazioni sull'API con descrizioni associate a ciascun endpoint specificato. 
        return apiInfo.toString();
    }

    // gestisce richieste HTTP GET all'endpoint "/products
    // @ResponseBody converte automaticamente in JSON la lista di prodotti (restituita dal servizio) direttamente nella risposta HTTP come corpo della risposta
    @GetMapping("/products")
    public @ResponseBody List<Product> getProducts() {
        // restituisce un elenco di tutti i prodotti ottenuti
        return service.getProducts();
    }

    @GetMapping("/product/{id}")
    // @PathVariable indica che l'ID del prodotto è estratto dalla variabile nelle parentesi graffe.
    public @ResponseBody Product getProduct(@PathVariable String id) {
        // Restituisce per id un singolo prodotto ottenuto 
        return service.getProduct(id);
    }

    @GetMapping("/products/category/{category}")
    // deserializzare automaticamente il corpo della richiesta in formato JSON, in lista di oggetti Product.
    public @ResponseBody Page<Product> findProducts(@PathVariable String category, Pageable page) {
        return service.findProducts(category, page);
    }

    @PostMapping("/addProduct")
    // RequestBody per deserializzare automaticamente il corpo della richiesta HTTP (in formato JSON) nell'oggetto Product.
    public Product addProduct(@RequestBody Product product) {
        // salvare il nuovo prodotto nel database.
        return service.saveProduct(product);
    }

    @PostMapping("/addProducts")
    // reqbody List<Product> deserializzare automaticamente il corpo della richiesta in formato JSON, in lista di oggetti Product.
    public List<Product> addProducts(@RequestBody List<Product> products) {
        // salvare la lista di prodotti nel database.
        return service.saveProducts(products);
    }

    @PutMapping("/products/{id}/availability/{amount}")
    // changeQuantity per modificare la quantità disponibile del prodotto nel database.
    public @ResponseBody Product changeQuantity(@PathVariable String id, @PathVariable int amount) {
        return service.changeQuantity(id, amount);
    }
}
