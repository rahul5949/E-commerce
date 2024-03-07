package com.nagarro.productservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openapitools.jackson.nullable.JsonNullable;

@Data
public class CategoryDto {
    private String name;
    private JsonNullable<String> description;
}
