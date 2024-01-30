package com.nagarro.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product extends BaseModel{
    private String title;
    private Double price;
    // cardinality between product and category
    @ManyToOne
    private Category category;

    private String description;

    private String imageUrl;

}
