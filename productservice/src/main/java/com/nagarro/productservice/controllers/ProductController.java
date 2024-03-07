package com.nagarro.productservice.controllers;

import com.nagarro.productservice.dto.ProductDto;
import com.nagarro.productservice.exceptions.ProductNotFoundException;
import com.nagarro.productservice.model.Product;
import com.nagarro.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public  ProductController(@Qualifier("productserviceimpl") ProductService productService){
        this.productService = productService;
    }
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts(){
       return ResponseEntity.ok(productService.getAllProducts());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getSingleProduct(id));
    }

    @PostMapping()
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto){

        return ResponseEntity.ok(productService.addNewProduct(productDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.replaceProduct(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));

    }

}
