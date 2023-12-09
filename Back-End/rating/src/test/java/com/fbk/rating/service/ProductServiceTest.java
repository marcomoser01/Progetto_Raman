package com.fbk.rating.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fbk.rating.domain.Product;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private RestTemplate restTemplateMock;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts_Success() {
        // Mocking the ResponseEntity
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Spazzolino", "Igiene", "lo spazolino è fatto di bambu", 5.00, 30));
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(productList, HttpStatus.OK);

        // Mocking the exchange method of RestTemplate to return the mocked
        // ResponseEntity
        when(restTemplateMock.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class))).thenReturn(responseEntity);

        // Calling the method under test
        List<Product> result = productService.getAllProducts();

        // Verifying that the method returns the expected result
        assertEquals(productList, result);
    }

    @Test
    public void testGetAllProducts_Failure() {
        // Mocking the ResponseEntity for failure scenario
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        // Mocking the exchange method of RestTemplate to return the mocked
        // ResponseEntity
        when(restTemplateMock.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class))).thenReturn(responseEntity);

        // Calling the method under test
        List<Product> result = productService.getAllProducts();

        // Verifying that the method returns an empty list in case of failure
        assertEquals(0, result.size());
    }

    // Similar test scenarios can be added for other methods in ProductService class
}
