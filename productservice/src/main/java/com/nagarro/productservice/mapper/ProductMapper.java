package com.nagarro.productservice.mapper;

import com.nagarro.productservice.dto.ProductDto;
import com.nagarro.productservice.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<ProductDto> productToProductDto(List<Product> product);
    ProductDto productToProductDto(Product products);
}
