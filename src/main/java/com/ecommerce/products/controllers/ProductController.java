package com.ecommerce.products.controllers;

import com.ecommerce.products.dtos.ProductDto;
import com.ecommerce.products.exceptions.ProductException;
import com.ecommerce.products.models.Product;
import com.ecommerce.products.services.interfaces.ProductService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<Product> save(@RequestBody @Validated(ProductDto.ProductView.RegistrationPost.class)
                                            @JsonView(ProductDto.ProductView.RegistrationPost.class)
                                            ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDto));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable(value = "productId") String productId) throws ProductException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(productId));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Product> update(@RequestBody @Validated(ProductDto.ProductView.ProductUp.class)
                                              @JsonView(ProductDto.ProductView.ProductUp.class) ProductDto productDto,
                                          @PathVariable(value = "productId") String productId) throws ProductException {
        var productModel = new Product();
        BeanUtils.copyProperties(productDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(productModel, productId));
    }

    @DeleteMapping("/{productId}")
    public void deleteById(@PathVariable(value = "productId") String productId) throws ProductException {
        productService.deleteById(productId);
    }
}
