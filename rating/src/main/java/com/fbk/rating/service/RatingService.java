package com.fbk.rating.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbk.rating.domain.Product;
import com.fbk.rating.domain.ProductAndAvg;
import com.fbk.rating.domain.Rating;
import com.fbk.rating.domain.RatingRequest;
import com.fbk.rating.domain.User;
import com.fbk.rating.repository.RatingRepository;

@Service
public class RatingService {

    //Inietta un'istanza di RatingRepository, gestire le operazioni sulle valutazioni nel database.(attributo)
    @Autowired
    private RatingRepository repo;

    //Inietta un'istanza di ProductService,gestisce l'accesso alle informazioni sui prodotti.
    @Autowired
    private ProductService productService;

    //  Inietta un'istanza di UserService, gestisce l'accesso alle informazioni sugli utenti.
    @Autowired
    private UserService userService;

    // Restituisce una lista di valutazioni associate a un utente specificato dall'ID.
    //Utilizza il metodo findByUserId del RatingRepository per ottenere la lista di valutazioni.
    public List<Rating> getRatingsByUser(String id) {
        return repo.findByUserId(Integer.parseInt(id));
    }

    //Restituisce una lista di valutazioni associate a un prodotto specificato dall'ID.
    //Utilizza il metodo findByProductId del RatingRepository per ottenere la lista di valutazioni.
    public List<Rating> getRatingByProduct(String id) {
        return repo.findByProductId(Integer.parseInt(id));
    }

    //Restituisce una lista di oggetti ProductAndAvg, che rappresentano prodotti con la relativa media di valutazione.
    public List<ProductAndAvg> getPopular() {
        //Utilizza il ProductService per ottenere tutti i prodotti.
        List<Product> products = productService.getAllProducts();
        List<ProductAndAvg> productAndAvgs = new ArrayList<>();
        for (Product product : products) {
            //Per ogni prodotto, recupera le valutazioni associate utilizzando getRatingByProduct e converte in stringa
            List<Rating> ratingList = getRatingByProduct(product.getId().toString());
            //calcola la media di questi voti. 
            //Il metodo orElse restituisce il risultato della media, o nel caso in cui lo stream sia vuoto restituire 0.0
            double votoMedio = ratingList.stream()
                    .mapToDouble(Rating::getVote) 
                    .average() 
                    .orElse(0.0);
            // Calcola la media dei voti per ciascun prodotto e crea un oggetto ProductAndAvg corrispondente.
            productAndAvgs.add(new ProductAndAvg(product, votoMedio));
        }
        return ProductAndAvg.sortList(productAndAvgs);
    }

    //Salva o aggiorna una valutazione associata a un utente e a un prodotto specificati.
    public Rating saveProduct(RatingRequest ratingReq, String productId, String userId) {
        //Utilizza userService e productService per ottenere informazioni su utente e prodotto.
        User user = userService.getUser(userId);
        Product product = productService.getProduct(productId);
        //Verifica se esiste già una valutazione associata a quel prodotto e utente, e in caso contrario, la crea.
        // Se l'utente o il prodotto non esistono nel sistema il metodo restituirà null,Altrimenti, restituirà l'oggetto Rating 
        if (user != null && product != null) {
            //cercare una valutazione esistente associata a quel prodotto e utente.
            Rating rating = getIdRating(productId, userId);
            //Se getIdRating restituisce null crea una nuova valutazione.
            if (rating == null) {
                // inizializzato un nuovo oggetto Rating
                rating = new Rating();
                // impostando i campi userId e productId con i valori forniti.
                rating.setuserId(userId);
                rating.setproductId(productId);
            }
            //  Imposta il messaggio
            rating.setMessage(ratingReq.getMessage());
            // Imposta il voto
            rating.setVote(ratingReq.getVote());
            //Aggiorna o imposta i dettagli della valutazione e la salva nel database utilizzando il RatingRepository.
            return repo.save(rating);
        }
        return null;
    }
    //Restituisce una valutazione associata a un prodotto e un utente specificati.
    public Rating getIdRating(String productId, String userId) {
        //Utilizza findByProductIdAndUserId del RatingRepository per ottenere la valutazione.
        Rating rating = repo.findByProductIdAndUserId(Integer.parseInt(productId),  Integer.parseInt(userId));
        // Se una valutazione viene trovata la restituisce, altrimenti restituisce null.
        if (rating != null) {
            return rating;
        }
        return null; 
    }

}
