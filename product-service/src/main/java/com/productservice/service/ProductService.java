package com.productservice.service;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .rating(productRequest.getRating())
                .category(productRequest.getCategory())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());

    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .rating(product.getRating())
                .category(product.getCategory())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

    }

    public ProductResponse getProductById(String productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        return mapToProductResponse(product);
    }

    public Product updateProduct(String productId, Product product) {
        Product _product = productRepository.findById(productId).orElseThrow();
        _product.setName(product.getName());
        _product.setDescription(product.getDescription());
        _product.setPrice(product.getPrice());
        return productRepository.save(_product);
    }

    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        productRepository.delete(product);
    }

    public List<Product> sortProduct(List<String> sortParams) {

        String sortColumn= sortParams.get(0);
        String direction = sortParams.get(1);

        if(direction.equals("desc")){
            return productRepository.findAll(Sort.by(Sort.Direction.DESC, sortColumn));
        }
        else{
            return productRepository.findAll(Sort.by(Sort.Direction.ASC, sortColumn));
        }

//        if(sortParams.get(0).equals("price")){
//            if (sortParams.get(1).equals("asc")) {
//                return productRepository.findAllByOrderByPriceAsc();
//            }
//            else{
//                return productRepository.findAllByOrderByPriceDesc();
//            }
//        }
//        if(sortParams.get(0).equals("rating")){
//            if (sortParams.get(1).equals("asc")) {
//                return productRepository.findAllByOrderByRatingAsc();
//            }
//            else{
//                return productRepository.findAllByOrderByRatingDesc();
//            }
//        }
//
//

    }

    public List<Product> filterByPrice(float startPrice, float endPrice) {
        List<Product>  product= productRepository.findAll();
        return product.stream().filter(p->p.getPrice()>startPrice)
                .filter(p->p.getPrice()<endPrice).collect(Collectors.toList());
    }

    public List<Product> filterByRating(float _minRating, float _maxRating) {
        List<Product>  product= productRepository.findAll();
        return product.stream().filter(p->p.getRating()>_minRating)
                .filter(p->p.getRating()<_maxRating).collect(Collectors.toList());
    }
}