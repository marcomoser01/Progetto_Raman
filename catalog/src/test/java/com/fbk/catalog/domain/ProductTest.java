package com.fbk.catalog.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    public void testProductGettersAndSetters() {
        // Create a Product instance
        Product product = new Product();

        // Set values using setter methods
        product.setId(1);
        product.setTitle("Test Product");
        product.setCategory("Test Category");
        product.setDescription("Test Description");
        product.setPrice(10.99);
        product.setQuantity(5);

        // Test getter methods to ensure they return the expected values
        assertThat(product.getId()).isEqualTo(1);
        assertThat(product.getTitle()).isEqualTo("Test Product");
        assertThat(product.getCategory()).isEqualTo("Test Category");
        assertThat(product.getDescription()).isEqualTo("Test Description");
        assertThat(product.getPrice()).isEqualTo(10.99);
        assertThat(product.getQuantity()).isEqualTo(5);
    }

    @Test
    public void testProductConstructor() {
        // Create a Product instance using the constructor
        Product product = new Product(1, "Test Product", "Test Category", "Test Description", 10.99, 5);

        // Test getter methods to ensure the constructor set the values correctly
        assertThat(product.getId()).isEqualTo(1);
        assertThat(product.getTitle()).isEqualTo("Test Product");
        assertThat(product.getCategory()).isEqualTo("Test Category");
        assertThat(product.getDescription()).isEqualTo("Test Description");
        assertThat(product.getPrice()).isEqualTo(10.99);
        assertThat(product.getQuantity()).isEqualTo(5);
    }
}
