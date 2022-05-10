package com.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponse {
//    private String id;
//    private String name;
//    private String description;
//    private BigDecimal price;

    private String id;
    private String name;
    private String category;
    private String description;
    private float price;
    private float rating;
}
