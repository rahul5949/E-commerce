package com.nagarro.productservice.controllers;

import com.nagarro.productservice.dto.ProductDto;
import com.nagarro.productservice.exceptions.ProductNotFoundException;
import com.nagarro.productservice.model.Product;
import com.nagarro.productservice.repository.ProductRepository;
import com.nagarro.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    /*@Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void testProductsSameAsService(){
       // arrange
        List<ProductDto> products = new ArrayList<>();
        ProductDto p1 = new ProductDto();
        p1.setTitle("iPhone 15");
        products.add(p1);
        ProductDto p2 = new ProductDto();
        p1.setTitle("iPhone 15 Pro ");
        products.add(p2);
        ProductDto p3 = new ProductDto();
        p1.setTitle("iPhone 15 Pro Max ");
        products.add(p3);

        when(
                productService.getAllProducts()
        ).thenReturn(
                products
        );

        //act
        ResponseEntity<List<ProductDto>> response =
                productController.getAllProducts();

        // asserts
        List<ProductDto> productInResponse = response.getBody();

        assert productInResponse != null;
        assertEquals(products.size(), productInResponse.size());
    }

    @Test
    void testNonExistingProductThrowsException(){
        // arrange
        when(
                productRepository.findById(10L)
        ).thenReturn(
                Optional.empty()
        );

        // act
        assertThrows(
                ProductNotFoundException.class,
                () -> productController.getSingleProduct(10L)
        );

    }*/

}