package com.fbk.rating.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RatingTest {

    @Test
    public void testRatingConstructors() {
        // Creazione di un oggetto Rating utilizzando il costruttore vuoto
        Rating emptyRating = new Rating();

        // Verifica che l'oggetto sia stato creato correttamente
        assertNotNull(emptyRating);

        // Creazione di un oggetto Rating utilizzando il costruttore con argomenti
        Rating filledRating = new Rating(1, 5, "Great product!", 123, 456);

        // Verifica che l'oggetto sia stato creato correttamente con i valori
        // specificati
        assertNotNull(filledRating);
        assertEquals(1, filledRating.getId());
        assertEquals(5, filledRating.getVote());
        assertEquals("Great product!", filledRating.getMessage());
        assertEquals(123, filledRating.getuserId());
        assertEquals(456, filledRating.getproductId());
    }

    @Test
    public void testRatingSettersAndGetters() {
        // Creazione di un oggetto Rating
        Rating rating = new Rating();

        // Impostazione dei valori tramite i metodi setter
        rating.setId(1);
        rating.setVote(5);
        rating.setMessage("Great product!");
        rating.setuserId(123); // Specifica l'ID dell'utente come Integer
        rating.setproductId("456"); // Specifica l'ID del prodotto come Stringa

        // Verifica che i metodi getter restituiscano i valori corretti
        assertEquals(1, rating.getId());
        assertEquals(5, rating.getVote());
        assertEquals("Great product!", rating.getMessage());
        assertEquals(123, rating.getuserId());
        assertEquals(456, rating.getproductId());
    }

}
