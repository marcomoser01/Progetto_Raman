package com.fbk.purchase.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.fbk.purchase.domain.Purchase;
import com.fbk.purchase.repository.PurchaseRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepositoryMock;

    @Mock
    private ProductService productServiceMock;

    @InjectMocks
    @Autowired
    private PurchaseService purchaseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserPurchases() {
        String userId = "1";
        List<Purchase> mockedPurchases = new ArrayList<>();
        mockedPurchases.add(new Purchase(1, "productId1", "Product Title 1", "Category", "User1", 20.0, 2));
        mockedPurchases.add(new Purchase(2, "productId2", "Product Title 2", "Category", "User1", 30.0, 1));

        when(purchaseRepositoryMock.findByUserId(userId)).thenReturn(Optional.of(mockedPurchases));

        Optional<List<Purchase>> userPurchases = purchaseService.getUserPurchases(userId);

        assertThat(userPurchases).isNotNull();
        assertThat(userPurchases).isEqualTo(Optional.of(mockedPurchases));
        verify(purchaseRepositoryMock, times(1)).findByUserId(userId);
    }

    @Test
    public void testGetUserPurchase() {
        String purchaseId = "1";
        Purchase purchase = new Purchase();
        when(purchaseRepositoryMock.findById(Integer.parseInt(purchaseId))).thenReturn(java.util.Optional.of(purchase));

        Purchase userPurchase = purchaseService.getUserPurchase(purchaseId);

        assertThat(userPurchase).isNotNull();
        assertThat(userPurchase).isEqualTo(purchase);
        verify(purchaseRepositoryMock, times(1)).findById(Integer.parseInt(purchaseId));
    }

}
