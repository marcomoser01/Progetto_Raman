package com.fbk.rating.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RatingRequestTest {

    @Test
    public void testRatingRequestGettersAndSetters() {
        // Creazione di un oggetto RatingRequest
        RatingRequest ratingRequest = new RatingRequest();

        // Impostazione dei valori utilizzando i setter
        ratingRequest.setVote(5);
        ratingRequest.setMessage("Great product!");

        // Verifica che i valori siano stati impostati correttamente tramite i setter
        assertEquals(5, ratingRequest.getVote());
        assertEquals("Great product!", ratingRequest.getMessage());
    }

    @Test
    public void testRatingRequestConstructor() {
        // Creazione di un oggetto RatingRequest utilizzando il costruttore con
        // argomenti
        RatingRequest ratingRequest = new RatingRequest(5, "Great product!");

        // Verifica che l'oggetto sia stato creato correttamente con i valori
        // specificati
        assertEquals(5, ratingRequest.getVote());
        assertEquals("Great product!", ratingRequest.getMessage());
    }
}
