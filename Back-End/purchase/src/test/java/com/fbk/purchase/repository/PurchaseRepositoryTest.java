package com.fbk.purchase.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fbk.purchase.domain.Purchase;
import com.fbk.purchase.service.PurchaseService;

@ExtendWith(MockitoExtension.class)
public class PurchaseRepositoryTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    @Test
    public void testFindByUserId() {
        String userId = "1";
        List<Purchase> mockedPurchases = new ArrayList<>();
        mockedPurchases.add(new Purchase(1, "productId1", "Product Title 1", "Category", "User1", 20.0, 2));
        mockedPurchases.add(new Purchase(2, "productId2", "Product Title 2", "Category", "User1", 30.0, 1));

        when(purchaseRepository.findByUserId(userId)).thenReturn(Optional.of(mockedPurchases));

        Optional<List<Purchase>> userPurchases = purchaseService.getUserPurchases(userId);

        assertThat(userPurchases).isNotNull();
        assertThat(userPurchases).isEqualTo(Optional.of(mockedPurchases));
        verify(purchaseRepository, times(1)).findByUserId(userId);
    }
}
