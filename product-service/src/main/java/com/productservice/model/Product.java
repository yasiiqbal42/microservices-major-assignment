package com.productservice.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value="product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id;
    //NEED TO IMPLEMENT THE RATINGS
    private String name;
    private String description;
    private BigDecimal price;
    //OPTIONAL NEED TO CREATE TYPE COLUMN AND CATEGORY COLUMN

}

