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

    @Autowired
    private RatingRepository repo;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public List<Rating> getRatingsByUser(String id) {
        return repo.findByUserId(Integer.parseInt(id));
    }

    public List<Rating> getRatingByProduct(String id) {
        return repo.findByProductId(Integer.parseInt(id));
    }

    public List<ProductAndAvg> getPopular() { 
        List<Product> products = productService.getAllProducts();
        List<ProductAndAvg> productAndAvgs = new ArrayList<>();
        for (Product product : products) {
            List<Rating> ratingList = getRatingByProduct(product.getId().toString());
            double votoMedio = ratingList.stream()
                    .mapToDouble(Rating::getVote) // Estrai il campo voto come double
                    .average() // Calcola la media
                    .orElse(0.0);
            productAndAvgs.add(new ProductAndAvg(product, votoMedio));
        }
        return ProductAndAvg.sortList(productAndAvgs);
    }

    public Rating saveRating(RatingRequest ratingReq, String productId, String userId) {
        User user = userService.getUser(userId);
        Product product = productService.getProduct(productId);
        if (user != null && product != null) {
            Rating rating = getIdRating(productId, userId);
            if (rating == null) {
                rating = new Rating();
                rating.setuserId(userId);
                rating.setproductId(productId);
            }
            rating.setMessage(ratingReq.getMessage());
            rating.setVote(ratingReq.getVote());
            return repo.save(rating);
        }
        return null;
    }

    public Rating getIdRating(String productId, String userId) {
        Rating rating = repo.findByProductIdAndUserId(Integer.parseInt(productId),  Integer.parseInt(userId));
        if (rating != null) {
            return rating;
        }
        return null; // Ritorna null se non esiste una recensione per l'utente e il prodotto
                     // specificati.
    }

    
    

}
