package com.nagarro.productservice.dto;

import com.nagarro.productservice.model.BaseModel;
import com.nagarro.productservice.model.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openapitools.jackson.nullable.JsonNullable;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDto extends BaseModel implements Serializable {
    private String title;
    private Double price;
    private CategoryDto category;
    private String imageUrl;

}
