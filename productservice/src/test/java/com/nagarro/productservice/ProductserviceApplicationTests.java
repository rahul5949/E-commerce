package com.nagarro.productservice;

import com.nagarro.productservice.model.Product;
import com.nagarro.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
class ProductserviceApplicationTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads(){

    }

    @Test
    @Transactional
    @Commit
    void testQueries(){
        productRepository.findByTitleContaining("rahul");
        productRepository.deleteByTitle("rahul");

        List<Product> products = productRepository.hqlQueriesExample();
    }

}
