package com.fbk.rating.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fbk.rating.domain.Product;
import com.fbk.rating.domain.Rating;
import com.fbk.rating.domain.RatingRequest;
import com.fbk.rating.domain.User;
import com.fbk.rating.domain.ValutazioneProdotto;
import com.fbk.rating.repository.RatingRepository;

@Service
public class RatingService {

    @Autowired
    private RatingRepository repo;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public Page<Rating> getRatingsByUser(String id, Pageable page) {
        return repo.findByUserId(Integer.parseInt(id), page);
    }

    public Page<Rating> getRatingByProduct(String id, Pageable page) {
        return repo.findByProductId(Integer.parseInt(id), page);
    }

    public List<Product> getPopular() {
        List<Product> products = productService.getAllProducts();
        List<ValutazioneProdotto> votiMedi = new ArrayList<>();
        for (Product product : products) {
            List<Rating> ratingList = getRatingByProduct(product.getId().toString(), null).getContent();
            double votoMedio = ratingList.stream()
                    .mapToDouble(Rating::getVote) // Estrai il campo voto come double
                    .average() // Calcola la media
                    .orElse(0.0);
            votiMedi.add(new ValutazioneProdotto(product.getId(), votoMedio));
        }
        return ordinaProdottiPerVotoMedioDecrescente(votiMedi, products);
    }

    public static List<Product> ordinaProdottiPerVotoMedioDecrescente(List<ValutazioneProdotto> valutazioni, List<Product> prodotti) {
        List<Product> prodottiOrdinati = valutazioni.stream()
                .sorted(Comparator.comparing(ValutazioneProdotto::getVotoMedio).reversed())
                .map(valutazione -> prodotti.stream()
                        .filter(prodotto -> prodotto.getId().equals(valutazione.getIdProdotto()))
                        .findFirst()
                        .orElse(null))
                .collect(Collectors.toList());

        return prodottiOrdinati;
    }

    public Rating saveProduct(RatingRequest ratingReq) {
        Rating rating = new Rating();
        User user = userService.getUser(ratingReq.getUser_id());
        Product product = productService.getProduct(ratingReq.getProduct_id());
        if (user != null && product != null) {
            rating.setMessage(ratingReq.getMessage());
            rating.setVote(ratingReq.getVote());
            rating.setuserId(ratingReq.getUser_id());
            rating.setproductId(ratingReq.getProduct_id());
            return repo.save(rating);
        }
        return rating;
    }

}
