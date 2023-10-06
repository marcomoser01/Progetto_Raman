package com.fbk.rating.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fbk.rating.domain.ProductAndAvg;
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
    public @ResponseBody List<Rating> getRatingsByUserId(@PathVariable String id) {
        return service.getRatingsByUser(id);
    }

    @PostMapping("/ratings/{productId}/{userId}")
    public Rating addRating(@RequestBody RatingRequest rating, @PathVariable String productId,
            @PathVariable String userId) {
        return service.saveProduct(rating, productId, userId);
    }

    @GetMapping("/ratings/{productId}")
    public @ResponseBody List<Rating> getRatingsByProductId(@PathVariable String productId) {
        return service.getRatingByProduct(productId);
    }

    @GetMapping("/popular")
    public @ResponseBody List<ProductAndAvg> getPopular() {
        return service.getPopular();
    }

}
