package com.nagarro.productservice.controllers;

import com.nagarro.productservice.commmons.AuthenticationCommons;
import com.nagarro.productservice.dto.ProductDto;
import com.nagarro.productservice.dto.Role;
import com.nagarro.productservice.dto.UserDto;
import com.nagarro.productservice.exceptions.ProductNotFoundException;
import com.nagarro.productservice.model.Product;
import com.nagarro.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class
ProductController {

    private final ProductService productService;
    private final RestTemplate restTemplate;
    private final AuthenticationCommons authenticationCommons;

    @Autowired
    public  ProductController(@Qualifier("fakeStoreProductService") ProductService productService, RestTemplate restTemplate, AuthenticationCommons authenticationCommons){
        this.productService = productService;
        this.restTemplate = restTemplate;
        this.authenticationCommons = authenticationCommons;

    }
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        /*UserDto userDto = authenticationCommons.validateToken(token);
        if(userDto == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        boolean isAdmin = false;
        for(Role role : userDto.getRoles()){
            if(role.getName().equals("ADMIN")){
                isAdmin = true;
                break;
            }
        }
        if(!isAdmin){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }*/
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
