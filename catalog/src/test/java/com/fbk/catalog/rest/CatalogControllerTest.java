package com.fbk.catalog.rest;

import com.fbk.catalog.domain.Product;
import com.fbk.catalog.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CatalogControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private CatalogController catalogController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProducts() {
        List<Product> productList = new ArrayList<>();
        // Aggiungi prodotti alla lista productList

        when(productService.getProducts()).thenReturn(productList);

        List<Product> result = catalogController.getProducts();

        assertEquals(productList, result);
        verify(productService, times(1)).getProducts();
    }

    @Test
    public void testGetProduct() {
        String productId = "1";
        Product expectedProduct = new Product(); // Crea un prodotto di esempio
        when(productService.getProduct(productId)).thenReturn(expectedProduct);

        Product result = catalogController.getProduct(productId);

        assertEquals(expectedProduct, result);
        verify(productService, times(1)).getProduct(productId);
    }

    @Test
    public void testFindProducts() {
        String category = "TestCategory";
        Pageable pageable = Pageable.unpaged();
        Page<Product> expectedPage = new PageImpl<>(new ArrayList<>()); // Crea una Page vuota come esempio
        when(productService.findProducts(category, pageable)).thenReturn(expectedPage);

        Page<Product> result = catalogController.findProducts(category, pageable);

        assertEquals(expectedPage, result);
        verify(productService, times(1)).findProducts(category, pageable);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product(); // Crea un nuovo prodotto per il test
        when(productService.saveProduct(product)).thenReturn(product);

        Product result = catalogController.addProduct(product);

        assertEquals(product, result);
        verify(productService, times(1)).saveProduct(product);
    }

    @Test
    public void testAddProducts() {
        List<Product> products = new ArrayList<>(); // Aggiungi prodotti alla lista products

        when(productService.saveProducts(products)).thenReturn(products);

        List<Product> result = catalogController.addProducts(products);

        assertEquals(products, result);
        verify(productService, times(1)).saveProducts(products);
    }

    @Test
    public void testChangeQuantity() {
        String productId = "1";
        int amount = 5;
        Product expectedProduct = new Product(); // Crea un prodotto di esempio
        when(productService.changeQuantity(productId, amount)).thenReturn(expectedProduct);

        Product result = catalogController.changeQuantity(productId, amount);

        assertEquals(expectedProduct, result);
        verify(productService, times(1)).changeQuantity(productId, amount);
    }

}
