package com.nagarro.productservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openapitools.jackson.nullable.JsonNullable;

import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {
    private String name;
    private JsonNullable<String> description;
}
