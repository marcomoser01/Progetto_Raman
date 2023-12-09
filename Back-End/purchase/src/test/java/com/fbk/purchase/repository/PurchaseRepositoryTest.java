package com.fbk.purchase.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
        // Mocking data
        int userId = 1;
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(new Purchase(1, "productId1", "Product Title 1", "Category", "User1", 20.0, 2));
        purchases.add(new Purchase(2, "productId2", "Product Title 2", "Category", "User1", 30.0, 1));

        Page<Purchase> purchasePage = new PageImpl<>(purchases);

        // Mocking repository method call
        when(purchaseRepository.findByUserId(userId, Pageable.unpaged())).thenReturn(purchasePage);

        // Call service method
        Page<Purchase> result = purchaseService.getUserPurchases(String.valueOf(userId), Pageable.unpaged());

        // Assert
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getContent().get(0).getId());
        assertEquals("Product Title 1", result.getContent().get(0).getProductTitle());
        assertEquals(2, result.getContent().get(1).getId());
        assertEquals("Product Title 2", result.getContent().get(1).getProductTitle());
    }
}
