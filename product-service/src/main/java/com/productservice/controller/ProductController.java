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
//    public ResponseEntity<ProductRequest> createProduct(@RequestBody ProductRequest productRequest, HttpServletRequest request) {
//        productService.createProduct(productRequest);
//        if (productRequest != null) {
//            try {
//                productService.createProduct(productRequest);
//                return new ResponseEntity<ProductRequest>(
//                        productRequest,
//                        headerGenerator.getHeadersForSuccessPostMethod(request),
//                        HttpStatus.CREATED);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return new ResponseEntity<ProductRequest>(
//                        headerGenerator.getHeadersForError(),
//                        HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//        return new ResponseEntity<ProductRequest>(
//                headerGenerator.getHeadersForError(),
//                HttpStatus.BAD_REQUEST);
//    }

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
}
