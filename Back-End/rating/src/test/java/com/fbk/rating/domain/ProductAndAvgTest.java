package com.fbk.rating.domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProductAndAvgTest {

    @Test
    public void testProductAndAvgConstructor() {
        // Creazione di un oggetto Product
        Product product = new Product();

        // Creazione di un oggetto ProductAndAvg usando il costruttore
        Double avgVote = 4.2;
        ProductAndAvg productAndAvg = new ProductAndAvg(product, avgVote);

        // Verifica che il costruttore inizializzi correttamente gli attributi
        assertEquals(product, productAndAvg.getProduct());
        assertEquals(avgVote, productAndAvg.getAvgVote());
    }

    @Test
    public void testProductAndAvgSettersAndGetters() {
        // Creazione di un oggetto Product
        Product product = new Product();

        // Creazione di un oggetto ProductAndAvg
        ProductAndAvg productAndAvg = new ProductAndAvg();

        // Impostazione dei valori tramite i metodi setter
        productAndAvg.setProduct(product);
        productAndAvg.setAvgVote(3.8);

        // Verifica che i metodi getter restituiscano i valori corretti
        assertEquals(product, productAndAvg.getProduct());
        assertEquals(3.8, productAndAvg.getAvgVote());
    }

    @Test
    public void testSortList() {
        // Creazione di oggetti Product
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        // Creazione di oggetti ProductAndAvg
        ProductAndAvg productAndAvg1 = new ProductAndAvg(product1, 4.5);
        ProductAndAvg productAndAvg2 = new ProductAndAvg(product2, 3.8);
        ProductAndAvg productAndAvg3 = new ProductAndAvg(product3, 5.0);

        // Creazione di una lista non ordinata di ProductAndAvg
        List<ProductAndAvg> unsortedList = new ArrayList<>();
        unsortedList.add(productAndAvg1);
        unsortedList.add(productAndAvg2);
        unsortedList.add(productAndAvg3);

        // Ordinamento della lista
        List<ProductAndAvg> sortedList = ProductAndAvg.sortList(unsortedList);

        // Verifica che la lista ordinata sia corretta
        assertEquals(productAndAvg3, sortedList.get(0)); // Il prodotto con il voto più alto è il primo
        assertEquals(productAndAvg1, sortedList.get(1)); // Il secondo prodotto con il voto più alto è il secondo
        assertEquals(productAndAvg2, sortedList.get(2)); // Il prodotto con il voto più basso è l'ultimo
    }
}
