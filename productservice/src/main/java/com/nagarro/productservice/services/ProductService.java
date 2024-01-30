package com.nagarro.productservice.services;

import com.nagarro.productservice.dto.ProductDto;
import com.nagarro.productservice.exceptions.ProductNotFoundException;
import com.nagarro.productservice.model.Product;

import java.util.List;

public interface ProductService {
    ProductDto getSingleProduct(Long id) throws ProductNotFoundException;

    ProductDto addNewProduct(Product product);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(Long id, Product product) throws ProductNotFoundException;

    ProductDto replaceProduct(Long id, Product product) throws ProductNotFoundException;

    void deleteProduct(Long id);
}
