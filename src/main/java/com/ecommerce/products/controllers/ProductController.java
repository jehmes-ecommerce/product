package com.ecommerce.products.controllers;

import com.ecommerce.products.dtos.ProductDto;
import com.ecommerce.products.models.Product;
import com.ecommerce.products.services.interfaces.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductDto productDto) {
        var newProduct = new Product();
        BeanUtils.copyProperties(productDto, newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(newProduct));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findAll(@PathVariable(value = "productId") String productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(productId));
    }
}
