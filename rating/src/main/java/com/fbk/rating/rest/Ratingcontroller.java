package com.fbk.rating.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.fbk.rating.domain.Rating;
import com.fbk.rating.domain.RatingRequest;
import com.fbk.rating.service.RatingService;

@RestController
@RequestMapping("/api")
public class Ratingcontroller {

    @Autowired
    private RatingService service;

    @GetMapping("/rating/{id}")
    public @ResponseBody Page<Rating> getRatingsByUserId(@PathVariable String id, Pageable page) {
        return service.getRatingsByUser(id, page);
    }

    @PostMapping("/addRating")
    public Rating addRating(@RequestBody RatingRequest rating) {
        return service.saveProduct(rating);
    }

}
