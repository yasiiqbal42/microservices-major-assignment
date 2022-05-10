package com.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequest {
//    private String name;
//    private String description;
//    private BigDecimal price;
//
//    private String id;
//    //NEED TO IMPLEMENT THE RATINGS
    private String name;
    private String category;
    private String description;
    private float price;
    private float rating;

}
