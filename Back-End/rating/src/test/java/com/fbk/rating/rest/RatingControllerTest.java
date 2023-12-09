package com.fbk.rating.rest;

import com.fbk.rating.domain.ProductAndAvg;
import com.fbk.rating.domain.Rating;
import com.fbk.rating.domain.RatingRequest;
import com.fbk.rating.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RatingControllerTest {

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private RatingController ratingController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inizializza i mock
    }

    @Test
    public void testGetRatingsByUserId() {
        String userId = "user123";
        List<Rating> expectedRatings = new ArrayList<>();
        // Aggiungi valutazioni alla lista expectedRatings

        when(ratingService.getRatingsByUser(userId)).thenReturn(expectedRatings);

        List<Rating> result = ratingController.getRatingsByUserId(userId);

        assertEquals(expectedRatings, result);
        verify(ratingService, times(1)).getRatingsByUser(userId);
    }

    @Test
    public void testAddRating() {
        RatingRequest ratingRequest = new RatingRequest();
        String productId = "prod123";
        String userId = "user123";
        Rating expectedRating = new Rating(); // Crea una valutazione di esempio

        when(ratingService.saveRating(ratingRequest, productId, userId)).thenReturn(expectedRating);

        Rating result = ratingController.addRating(ratingRequest, productId, userId);

        assertEquals(expectedRating, result);
        verify(ratingService, times(1)).saveRating(ratingRequest, productId, userId);
    }

    @Test
    public void testGetRatingsByProductId() {
        String productId = "prod123";
        List<Rating> expectedRatings = new ArrayList<>();
        // Aggiungi valutazioni alla lista expectedRatings

        when(ratingService.getRatingByProduct(productId)).thenReturn(expectedRatings);

        List<Rating> result = ratingController.getRatingsByProductId(productId);

        assertEquals(expectedRatings, result);
        verify(ratingService, times(1)).getRatingByProduct(productId);
    }

    @Test
    public void testGetPopular() {
        List<ProductAndAvg> expectedPopularProducts = new ArrayList<>();
        // Aggiungi prodotti popolari alla lista expectedPopularProducts

        when(ratingService.getPopular()).thenReturn(expectedPopularProducts);

        List<ProductAndAvg> result = ratingController.getPopular();

        assertEquals(expectedPopularProducts, result);
        verify(ratingService, times(1)).getPopular();
    }
}
