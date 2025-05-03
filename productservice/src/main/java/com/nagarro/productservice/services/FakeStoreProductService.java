package com.nagarro.productservice.services;

import com.nagarro.productservice.dto.CategoryDto;
import com.nagarro.productservice.dto.FakeStoreProductDto;
import com.nagarro.productservice.dto.ProductDto;
import com.nagarro.productservice.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    private ProductDto convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProduct) {
        ProductDto product = new ProductDto();
        product.setTitle(fakeStoreProduct.getTitle());
        product.setId(fakeStoreProduct.getId());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setImageUrl(fakeStoreProduct.getImage());
        CategoryDto category = new CategoryDto();
        category.setName(fakeStoreProduct.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public ProductDto getSingleProduct(Long id) throws ProductNotFoundException {
        // Try to get product from Redis cache
        ProductDto cachedProduct = (ProductDto) redisTemplate.opsForValue().get("PRODUCT_" + id);

        if (cachedProduct != null) {
            return cachedProduct;
        }

        // If not found in cache, fetch from FakeStore API
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

        if (!responseEntity.getStatusCode().is2xxSuccessful() || responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product with id: " + id + " doesn't exist.");
        }

        ProductDto productDto = convertFakeStoreProductToProduct(responseEntity.getBody());

        // Save the fetched product into Redis cache
        redisTemplate.opsForValue().set("PRODUCT_" + id, productDto);

        return productDto;
    }

    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
        // Not implemented yet
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        List<ProductDto> products = new ArrayList<>();
        if (response != null) {
            for (FakeStoreProductDto dto : response) {
                products.add(convertFakeStoreProductToProduct(dto));
            }
        }
        return products;
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) throws ProductNotFoundException {
        // Not implemented yet
        return null;
    }

    @Override
    public ProductDto replaceProduct(Long id, ProductDto product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT,
                requestCallback,
                responseExtractor
        );

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public void deleteProduct(Long id) {
        // Not implemented yet
    }
}
