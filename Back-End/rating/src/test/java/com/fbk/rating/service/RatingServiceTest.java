package com.fbk.rating.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fbk.rating.domain.Product;
import com.fbk.rating.domain.ProductAndAvg;
import com.fbk.rating.domain.Rating;
import com.fbk.rating.domain.RatingRequest;
import com.fbk.rating.domain.User;
import com.fbk.rating.repository.RatingRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepositoryMock;

    @Mock
    private ProductService productServiceMock;

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private RatingService ratingService;

    @Test
    public void testGetRatingsByUser() {
        String userId = "1";
        List<Rating> expectedRatings = new ArrayList<>(); // Mocked list of ratings

        // Mocking the behavior of the repository method
        when(ratingRepositoryMock.findByUserId(Integer.parseInt(userId))).thenReturn(expectedRatings);

        // Calling the method under test
        List<Rating> result = ratingService.getRatingsByUser(userId);

        // Verifying that the method returns the expected result
        assertEquals(expectedRatings, result);
    }

    @Test
    public void testGetRatingByProduct() {
        String productId = "1";
        List<Rating> expectedRatings = new ArrayList<>(); // Mocked list of ratings

        // Mocking the behavior of the repository method
        when(ratingRepositoryMock.findByProductId(Integer.parseInt(productId))).thenReturn(expectedRatings);

        // Calling the method under test
        List<Rating> result = ratingService.getRatingByProduct(productId);

        // Verifying that the method returns the expected result
        assertEquals(expectedRatings, result);
    }

    @Test
    void testGetPopular() {

        Product prod1 = new Product(1, "Prod 1", "", "", 3.0, 20);
        Product prod2 = new Product(2, "Prod 2", "", "", 3.0, 20);
        List<Product> mockedProducts = Arrays.asList(prod1, prod2);

        User user1 = new User(1, "User 1");
        User user2 = new User(2, "User 2");

        // Simula la lista di Rating per i prodotti
        List<Rating> ratingListProduct1 = Arrays.asList(
                new Rating(1, 4, "message", user1.getId(), prod1.getId()),
                new Rating(1, 5, "message", user2.getId(), prod1.getId()));

        List<Rating> ratingListProduct2 = Arrays.asList(
                new Rating(1, 3, "message", user1.getId(), prod2.getId()),
                new Rating(1, 4, "message", user2.getId(), prod2.getId()));

        // Simula il comportamento di getAllProducts() del ProductService
        when(productServiceMock.getAllProducts()).thenReturn(mockedProducts);

        // Simula il comportamento di getRatingByProduct() per ogni prodotto
        when(ratingService.getRatingByProduct("1")).thenReturn(ratingListProduct1);
        when(ratingService.getRatingByProduct("2")).thenReturn(ratingListProduct2);

        // Esegui il metodo da testare
        List<ProductAndAvg> popularProducts = ratingService.getPopular();

        // Verifica che il risultato sia corretto
        // In base ai dati simulati, verifica la corrispondenza dei valori attesi
        assertEquals(4.5, popularProducts.get(0).getAvgVote()); // Media dei voti per Product 1
        assertEquals(3.5, popularProducts.get(1).getAvgVote()); // Media dei voti per Product 2
        // Aggiungi altri controlli se necessario per altri prodotti

        // Verifica che i metodi del ProductService siano stati chiamati
        verify(productServiceMock, times(1)).getAllProducts();
    }


    @Test
    public void testSaveRating_WhenUserAndProductExist() {
        // Dati di esempio per il test
        String userId = "1";
        String productId = "1";
        RatingRequest ratingRequest = new RatingRequest(5, "Great product!");

        // Simulazione di esistenza di user e product
        User mockUser = new User(1, "John");
        when(userServiceMock.getUser(userId)).thenReturn(mockUser);

        Product mockProduct = new Product(1, "Product A", "Description", "Category", 4.5, 100);
        when(productServiceMock.getProduct(productId)).thenReturn(mockProduct);

        // Simula che non esista una valutazione per il productId e userId forniti
        when(ratingRepositoryMock.findByProductIdAndUserId(Integer.parseInt(productId), Integer.parseInt(userId)))
            .thenReturn(null);

        // Comportamento atteso: un nuovo oggetto Rating viene creato e salvato nel repository
        Rating expectedRating = new Rating(1, 5, "Bel prodotto", Integer.parseInt(userId), Integer.parseInt(productId));
        when(ratingRepositoryMock.save(any(Rating.class))).thenReturn(expectedRating);

        // Esegui il metodo da testare
        Rating savedRating = ratingService.saveRating(ratingRequest, productId, userId);

        // Verifica che il rating salvato non sia nullo
        assertNotNull(savedRating);

        // Verifica che il rating restituito corrisponda al rating atteso
        assertEquals(expectedRating, savedRating);

        // Verifica che i metodi dei servizi siano stati chiamati correttamente
        verify(userServiceMock, times(1)).getUser(userId);
        verify(productServiceMock, times(1)).getProduct(productId);
        verify(ratingRepositoryMock, times(1))
            .findByProductIdAndUserId(Integer.parseInt(productId), Integer.parseInt(userId));
        verify(ratingRepositoryMock, times(1)).save(any(Rating.class));
        verifyNoMoreInteractions(userServiceMock, productServiceMock, ratingRepositoryMock);
    }

    @Test
    public void testGetIdRating() {
        String productId = "1";
        String userId = "1";
        Rating expectedRating = new Rating(); // Mocked Rating object

        // Mocking the behavior of the repository method
        when(ratingRepositoryMock.findByProductIdAndUserId(Integer.parseInt(productId), Integer.parseInt(userId)))
                .thenReturn(expectedRating);

        // Calling the method under test
        Rating result = ratingService.getIdRating(productId, userId);

        // Verifying that the method returns the expected result
        assertEquals(expectedRating, result);
    }
}
