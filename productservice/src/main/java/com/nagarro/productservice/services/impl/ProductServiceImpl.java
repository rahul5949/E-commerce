package com.nagarro.productservice.services.impl;

import com.nagarro.productservice.dto.CategoryDto;
import com.nagarro.productservice.dto.ProductDto;
import com.nagarro.productservice.exceptions.ProductNotFoundException;
import com.nagarro.productservice.mapper.CategoryMapper;
import com.nagarro.productservice.mapper.ProductMapper;
import com.nagarro.productservice.model.Category;
import com.nagarro.productservice.model.Product;
import com.nagarro.productservice.repository.CategoryRepository;
import com.nagarro.productservice.repository.ProductRepository;
import com.nagarro.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productserviceimpl")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    private  final CategoryMapper categoryMapper;

    @Autowired
    public  ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper, CategoryMapper categoryMapper){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;

        this.categoryMapper = categoryMapper;
    }

    @Override
    public ProductDto getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty() || productOptional.get().isDeleted()){
            throw new ProductNotFoundException("Product with id " + id + "does not exist");
        }
        return productMapper.productToProductDto(productOptional.get());
    }

    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository
                .findByName(categoryMapper.categoryDtoToCategory(productDto.getCategory()).getName());

        optionalCategory.ifPresent(category -> productDto.setCategory(categoryMapper.categoryToCategoryDto(category)));
        return productMapper.productToProductDto(productRepository.save(productMapper.productDtoToProduct(productDto)));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<Product> finalProducts = products.stream().filter(e -> !e.isDeleted()).toList();

       return productMapper.productToProductDto(finalProducts);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with ID" +id + " not exist");
        }
        return productMapper.productToProductDto(productRepository.save(productMapper.updateProductFromDTO(productDto, productOptional.get())));
    }
    @Override
    public ProductDto replaceProduct(Long id, ProductDto productDto) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("the product with ID" + id  + " does not exist");
        }
        return productMapper.productToProductDto(productRepository.save(productMapper.productDtoToProduct(productDto)));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
