package com.nagarro.productservice.repository;

import com.nagarro.productservice.dto.ProductDto;
import com.nagarro.productservice.model.Category;
import com.nagarro.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitleContaining(String word);

    long deleteByTitle(String title);

    @Modifying
    @Query("update Product e set e.isDeleted = true where e.id = :id")
    void deleteById(String id);



    List<Product> findByTitleAndDescription(String title, String description);

    List<Product> findByPriceBetween(double startRange, double endRange);

    List<Product> findByCategory(Category category);

    Product findByIdAndCategoryOrderByTitle(Long id, Category category);

    List<Product> findByCategory_Id(Long id);

    Optional<Product> findById(Long id);

    Product save(Product product);

   @Query("select p from Product p where p.price >100 and p.description like '%Iphone%'")
    List<Product> hqlQueriesExample();

}

// 1. CategoryRepository.findById()
// 2. ProductRepository.findByCategory()
// Problem: API may be bit slower because of 2 DB Calls
