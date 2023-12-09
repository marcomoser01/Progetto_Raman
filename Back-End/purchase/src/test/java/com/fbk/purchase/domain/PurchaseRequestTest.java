package com.fbk.purchase.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PurchaseRequestTest {

    @Test
    public void testGetSetProductId() {
        // Arrange
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        String productId = "ABC123";

        // Act
        purchaseRequest.setProductId(productId);
        String retrievedProductId = purchaseRequest.getProductId();

        // Assert
        assertEquals(productId, retrievedProductId);
    }

    @Test
    public void testGetSetCount() {
        // Arrange
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        int count = 5;

        // Act
        purchaseRequest.setCount(count);
        int retrievedCount = purchaseRequest.getCount();

        // Assert
        assertEquals(count, retrievedCount);
    }
}
