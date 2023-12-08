package com.fbk.rating.rest;

import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fbk.rating.domain.ProductAndAvg;
import com.fbk.rating.domain.Rating;
import com.fbk.rating.domain.RatingRequest;
import com.fbk.rating.service.RatingService;

//gestire le richieste HTTP e restituire le risposte come dati JSON (o XML) in base ai casi d'uso.
@RestController
//Specifica il prefisso per tutte le richieste mappate 
@RequestMapping("/api")
//specificare quali domini possono accedere alle risorse fornite dal controller.
@CrossOrigin
public class RatingController {
    //utilizzata per l'iniezione di dipendenze automatica
    //riduce configurazioni manuali
    @Autowired
    //attributo
    private RatingService service;
    //gestirà le richieste HTTP GET indirizzate a "/api/info"
    @GetMapping("/info")
    public String apiInfo() {
        // costruire un oggetto JSON (apiInfo) che rappresenta informazioni sull'API, 
        // fornendo descrizioni per diverse operazioni. Infine, viene restituito un oggetto JSON in formato stringa.
        JSONObject apiInfo = new JSONObject();
        //viene convertito in una stringa JSON utilizzando il metodo toString().
        apiInfo.put("GET /rating/{id}", "Restituisce le valutazioni per un utente specifico.");
        apiInfo.put("POST /ratings/{productId}/{userId}","Aggiunge una valutazione per un prodotto specifico da parte di un utente.");
        apiInfo.put("GET /ratings/{productId}", "Restituisce le valutazioni per un prodotto specifico.");
        apiInfo.put("GET /popular", "Restituisce i prodotti popolari basati sulle valutazioni.");
        //La stringa JSON risultante contiene le informazioni sull'API con descrizioni associate a ciascun endpoint specificato. 
        return apiInfo.toString();
    }
    // gestisce le richieste GET all'endpoint "/rating/{id}"
    @GetMapping("/rating/{id}")
    // aspetta un parametro di percorso {id} e restituisce una lista di oggetti
    // @ResponseBody converte automaticamente in JSON la lista di prodotti (restituita dal servizio) direttamente nella risposta HTTP come corpo della risposta
    //@PathVariable prende il valore dal URL
    public @ResponseBody List<Rating> getRatingsByUserId(@PathVariable String id) {
        // restituisce una lista di oggetti
        return service.getRatingsByUser(id);
    }

    // consentendo richieste da domini diversi.
    @CrossOrigin
    // gestisce le richieste POST all'endpoint "/ratings/{productId}/{userId}"
    @PostMapping("/ratings/{productId}/{userId}")
    // @PathVariable si aspetta due paramenti che prende dal URL 
    public Rating addRating(@RequestBody RatingRequest rating, @PathVariable String productId,@PathVariable String userId) {
        // codice chiama un metodo saveProduct su un oggetto di servizio (service) e restituisce il risultato di questa chiamata.
        return service.saveProduct(rating, productId, userId);
    }

    // Gestisce le richieste GET all'endpoint "/ratings/{productId}"
    @GetMapping("/ratings/{productId}")
    // @ResponseBody converte automaticamente in JSON la lista di prodotti (restituita dal servizio) direttamente nella risposta HTTP come corpo della risposta
    // pathvariable prende la variablile dal URL
    public @ResponseBody List<Rating> getRatingsByProductId(@PathVariable String productId) {
        // restituisce una lista di oggetti 
        return service.getRatingByProduct(productId);
    }
    // Gestisce le richieste GET all'endpoint "/popular"
    @GetMapping("/popular")
    // chiama un servizio per ottenere i prodotti più popolari
    public @ResponseBody List<ProductAndAvg> getPopular() {
        // Restituisce una lista di oggetti 
        return service.getPopular();
    }

}
