package com.nagarro.productservice.services;

import com.nagarro.productservice.dto.ProductDto;
import com.nagarro.productservice.exceptions.ProductNotFoundException;
import com.nagarro.productservice.model.Product;

import java.util.List;

public interface ProductService {
    ProductDto getSingleProduct(Long id) throws ProductNotFoundException;

    ProductDto addNewProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(Long id, ProductDto productDto) throws ProductNotFoundException;

    ProductDto replaceProduct(Long id, ProductDto productDto) throws ProductNotFoundException;

    void deleteProduct(Long id);
}
