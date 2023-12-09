package com.fbk.purchase.domain;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PurchaseTest {

    @InjectMocks
    private Purchase purchase;

    @Test
    public void testPurchaseConstructor() {
        // Setup
        Integer id = 1;
        String productId = "PROD-001";
        String productTitle = "Product 1";
        String productCategory = "Category 1";
        String userId = "USER-001";
        Double price = 10.0;
        Integer quantity = 2;

        Purchase purchase = new Purchase(id, productId, productTitle, productCategory, userId, price, quantity);

        // Verify
        assertNotNull(purchase);
        assertEquals(id, purchase.getId());
        assertEquals(productId, purchase.getProductId());
        assertEquals(productTitle, purchase.getProductTitle());
        assertEquals(productCategory, purchase.getProductCategory());
        assertEquals(userId, purchase.getUserId());
        assertEquals(price, purchase.getPrice());
        assertEquals(quantity, purchase.getQuantity());
    }

    @Test
    public void testGettersAndSetters() {
        purchase = new Purchase();
        // Setup
        Integer id = 1;
        String productId = "PROD-001";
        String productTitle = "Product 1";
        String productCategory = "Category 1";
        String userId = "USER-001";
        Double price = 10.0;
        Integer quantity = 2;

        purchase.setId(id);
        purchase.setProductId(productId);
        purchase.setProductTitle(productTitle);
        purchase.setProductCategory(productCategory);
        purchase.setUserId(userId);
        purchase.setPrice(price);
        purchase.setQuantity(quantity);

        // Verify
        assertEquals(id, purchase.getId());
        assertEquals(productId, purchase.getProductId());
        assertEquals(productTitle, purchase.getProductTitle());
        assertEquals(productCategory, purchase.getProductCategory());
        assertEquals(userId, purchase.getUserId());
        assertEquals(price, purchase.getPrice());
        assertEquals(quantity, purchase.getQuantity());
    }
}

