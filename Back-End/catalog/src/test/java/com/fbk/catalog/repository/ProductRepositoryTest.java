package com.fbk.catalog.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.fbk.catalog.domain.Product;
import com.fbk.catalog.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testFindByCategory() {
        // Mocking the behavior of the repository method findByCategory
        String category = "TestCategory";
        Product product1 = new Product();
        product1.setTitle("Product1");
        product1.setCategory(category);
        productService.saveProduct(product1);

        Product product2 = new Product();
        product2.setTitle("Product2");
        product2.setCategory(category);
        productService.saveProduct(product2);

        Product product3 = new Product();
        product3.setTitle("Product3");
        product3.setCategory(category);
        productService.saveProduct(product3);

        // Creating a Page with the saved products
        List<Product> savedProducts = Arrays.asList(product1, product2, product3);
        Page<Product> mockedPage = new PageImpl<>(savedProducts);

        when(productRepositoryMock.findByCategory(category, Pageable.unpaged())).thenReturn(mockedPage);

        // Calling the service method that uses the repository
        Page<Product> foundProducts = productService.findProducts(category, Pageable.unpaged());

        // Assertions
        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts.getContent()).hasSize(3); // Assuming 3 products for the test category
        assertThat(foundProducts.getContent().get(0).getTitle()).isEqualTo("Product1");
        assertThat(foundProducts.getContent().get(1).getTitle()).isEqualTo("Product2");
        assertThat(foundProducts.getContent().get(2).getTitle()).isEqualTo("Product3");
    }

    @Test
    public void testFindById() {
        // Mocking the behavior of the repository method findById
        int productId = 1;
        Product mockedProduct = new Product();
        mockedProduct.setId(productId);
        mockedProduct.setTitle("Test Product");
        when(productRepositoryMock.findById(productId)).thenReturn(Optional.of(mockedProduct));

        // Calling the service method that uses the repository
        Product foundProduct = productService.getProduct(String.valueOf(productId));

        // Assertions
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(productId);
        assertThat(foundProduct.getTitle()).isEqualTo("Test Product");
    }
}
