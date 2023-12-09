package com.fbk.purchase.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fbk.purchase.domain.Purchase;
import com.fbk.purchase.repository.PurchaseRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        Pageable pageable = Pageable.unpaged();
        Page<Purchase> mockedPage = mock(Page.class);
        when(purchaseRepositoryMock.findByUserId(Integer.parseInt(userId), pageable)).thenReturn(mockedPage);

        Page<Purchase> userPurchases = purchaseService.getUserPurchases(userId, pageable);

        assertThat(userPurchases).isNotNull();
        assertThat(userPurchases).isEqualTo(mockedPage);
        verify(purchaseRepositoryMock, times(1)).findByUserId(Integer.parseInt(userId), pageable);
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
