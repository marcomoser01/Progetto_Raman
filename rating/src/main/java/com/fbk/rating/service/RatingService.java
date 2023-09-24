package com.fbk.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fbk.rating.domain.Product;
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

    public Page<Rating> getRatingsByUser(String userId, Pageable page) {
        return repo.findByUserId(userId, page);
    }

    public Rating saveProduct(RatingRequest ratingReq) {
        Rating rating = new Rating();
        User user = userService.getUser(ratingReq.getUser_id());
        Product product = productService.getProduct(ratingReq.getProduct_id());
        if(user != null && product != null) {
            rating.setMessage(ratingReq.getMessage());
            rating.setVote(ratingReq.getVote());
            rating.setuserId(ratingReq.getUser_id());
            rating.setproductId(ratingReq.getProduct_id());
            return repo.save(rating);
        }
        return rating;
    }

}
