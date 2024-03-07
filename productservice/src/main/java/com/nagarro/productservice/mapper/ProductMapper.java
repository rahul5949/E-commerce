package com.nagarro.productservice.mapper;

import com.nagarro.productservice.dto.CategoryDto;
import com.nagarro.productservice.dto.ProductDto;
import com.nagarro.productservice.model.Category;
import com.nagarro.productservice.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = JsonNullableMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface ProductMapper {
    List<ProductDto> productToProductDto(List<Product> product);
    ProductDto productToProductDto(Product products);

    Product productDtoToProduct(ProductDto productDto);

    Product updateProductFromDTO(ProductDto productDto, @MappingTarget Product product);

    Category categoryDtoToCategory(CategoryDto categoryDto);
    CategoryDto categoryToCategoryDto(Category category);



}
