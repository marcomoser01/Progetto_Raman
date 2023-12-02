package com.fbk.rating.rest;

import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fbk.rating.domain.ProductAndAvg;
import com.fbk.rating.domain.Rating;
import com.fbk.rating.domain.RatingRequest;
import com.fbk.rating.service.RatingService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RatingController {

    @Autowired
    private RatingService service;

    @GetMapping("/info")
    public String apiInfo() {
        JSONObject apiInfo = new JSONObject();

        apiInfo.put("GET /rating/{id}", "Restituisce le valutazioni per un utente specifico.");
        apiInfo.put("POST /ratings/{productId}/{userId}",
                "Aggiunge una valutazione per un prodotto specifico da parte di un utente.");
        apiInfo.put("GET /ratings/{productId}", "Restituisce le valutazioni per un prodotto specifico.");
        apiInfo.put("GET /popular", "Restituisce i prodotti popolari basati sulle valutazioni.");

        return apiInfo.toString();
    }

    @GetMapping("/rating/{id}")
    public @ResponseBody List<Rating> getRatingsByUserId(@PathVariable String id) {
        return service.getRatingsByUser(id);
    }

    @CrossOrigin
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
