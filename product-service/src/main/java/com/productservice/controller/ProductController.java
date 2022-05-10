package com.productservice.controller;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.httpHeader.HeaderGenerator;
import com.productservice.model.Product;
import com.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private HeaderGenerator headerGenerator;

    @Autowired
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable(value = "productId") String productId, @RequestBody ProductRequest productRequest) {
        return productService.getProductById(productId);

    }


    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@PathVariable(value = "productId") String productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable(value = "productId") String productId){
        productService.deleteProduct(productId);
    }

    @GetMapping("/sortBy")
    public List<Product> sortProduct(@RequestParam String sort){
        List<String> sortParams= Arrays.stream(sort.split("_")).toList();
        return productService.sortProduct(sortParams);

    }


    @GetMapping("/filter/price")
    public List<Product> filterByPrice(@RequestParam String startPrice, @RequestParam String endPrice){
        float priceStart= Float.parseFloat(startPrice);
        float priceEnd=Float.parseFloat(endPrice);
        return productService.filterByPrice(priceStart,priceEnd);

    }
    @GetMapping("/filter/rating")
    public List<Product> filterByRating(@RequestParam String minRating, @RequestParam String maxRating) {
        float _minRating = Float.parseFloat(minRating);
        float _maxRating = Float.parseFloat(maxRating);
        return productService.filterByRating(_minRating, _maxRating);
    }
}
