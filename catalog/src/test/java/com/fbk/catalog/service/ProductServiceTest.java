package com.fbk.catalog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fbk.catalog.domain.Product;
import com.fbk.catalog.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setTitle("Test Product");
        when(productRepositoryMock.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getTitle()).isEqualTo("Test Product");
        verify(productRepositoryMock, times(1)).save(product);
    }

    @Test
    public void testSaveProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productRepositoryMock.saveAll(productList)).thenReturn(productList);

        List<Product> savedProducts = productService.saveProducts(productList);

        assertThat(savedProducts).isNotNull();
        assertThat(savedProducts).hasSize(2);
        verify(productRepositoryMock, times(1)).saveAll(productList);
    }

    @Test
    public void testGetProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productRepositoryMock.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = productService.getProducts();

        assertThat(retrievedProducts).isNotNull();
        assertThat(retrievedProducts).hasSize(2);
        verify(productRepositoryMock, times(1)).findAll();
    }

    // Test for getProducts(Pageable page)
    @Test
    public void testGetProductsageable() {
        Pageable pageable = Pageable.unpaged();
        Page<Product> mockedPage = mock(Page.class);
        when(productRepositoryMock.findAll(pageable)).thenReturn(mockedPage);

        Page<Product> foundProducts = productService.getProducts(pageable);

        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts).isEqualTo(mockedPage);
        verify(productRepositoryMock, times(1)).findAll(pageable);
    }

    // Test for getProduct(String id)
    @Test
    public void testGetProduct() {
        String id = "1";
        Product product = new Product();
        when(productRepositoryMock.findById(Integer.parseInt(id))).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProduct(id);

        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct).isEqualTo(product);
        verify(productRepositoryMock, times(1)).findById(Integer.parseInt(id));
    }

    // Test for findProducts(String category, Pageable page)
    @Test
    public void testFindProducts() {
        String category = "TestCategory";
        Pageable pageable = Pageable.unpaged();
        Page<Product> mockedPage = mock(Page.class);
        when(productRepositoryMock.findByCategory(category, pageable)).thenReturn(mockedPage);

        Page<Product> foundProducts = productService.findProducts(category, pageable);

        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts).isEqualTo(mockedPage);
        verify(productRepositoryMock, times(1)).findByCategory(category, pageable);
    }

    // Test for changeQuantity(String id, int amount)
    @Test
    public void testChangeQuantity() {
        String id = "1";
        int amount = 5;
        Product product = new Product();
        product.setId(Integer.parseInt(id));
        product.setQuantity(10);

        when(productRepositoryMock.findById(Integer.parseInt(id))).thenReturn(Optional.of(product));
        when(productRepositoryMock.save(product)).thenReturn(product);

        Product changedProduct = productService.changeQuantity(id, amount);

        assertThat(changedProduct).isNotNull();
        assertThat(changedProduct.getQuantity()).isEqualTo(5); // Assuming the initial quantity is 10
        verify(productRepositoryMock, times(1)).findById(Integer.parseInt(id));
        verify(productRepositoryMock, times(1)).save(product);
    }

}