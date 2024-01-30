package com.nagarro.productservice.dto;

import com.nagarro.productservice.model.BaseModel;
import com.nagarro.productservice.model.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDto extends BaseModel {
    private String title;
    private Double price;
    private Category category;
    private String description;
    private String imageUrl;

}
