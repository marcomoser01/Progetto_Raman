package com.fbk.rating.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.fbk.rating.domain.Product;
import com.fbk.rating.domain.Rating;
import com.fbk.rating.domain.RatingRequest;
import com.fbk.rating.service.RatingService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Ratingcontroller {

    @Autowired
    private RatingService service;

    @GetMapping("/rating/{id}")
    public @ResponseBody Page<Rating> getRatingsByUserId(@PathVariable String id, Pageable page) {
        return service.getRatingsByUser(id, page);
    }

    @PostMapping("/ratings/{productId}/{userId}")
    public Rating addRating(@RequestBody RatingRequest rating, @PathVariable String productId,
            @PathVariable String userId) {
        rating.setProduct_id(productId);
        rating.setUser_id(userId);
        return service.saveProduct(rating);
    }

    @GetMapping("/ratings/{productId}")
    public @ResponseBody Page<Rating> getRatingsByProductId(@PathVariable String productId, Pageable page) {
        return service.getRatingByProduct(productId, page);
    }

    @GetMapping("/popular")
    public @ResponseBody List<Product> getPopular() {
        return service.getPopular();
    }

}
