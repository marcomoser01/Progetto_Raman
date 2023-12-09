package com.fbk.purchase.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.fbk.purchase.domain.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private RestTemplate restTemplateMock;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProduct() {
        // Given
        String productId = "123";
        String endpoint = "http://gateway:9999/catalog";
        String productApi = endpoint + "/api/product/" + productId;
        Product expectedProduct = new Product(/* Set your expected product details */);

        when(restTemplateMock.getForObject(productApi, Product.class)).thenReturn(expectedProduct);

        // When
        Product actualProduct = productService.getProduct(productId);

        // Then
        verify(restTemplateMock, times(1)).getForObject(productApi, Product.class);
        assertEquals(expectedProduct, actualProduct);
    }

}
