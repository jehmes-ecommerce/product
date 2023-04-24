package com.ecommerce.products.services.interfaces;

import com.ecommerce.products.dtos.ProductDto;
import com.ecommerce.products.models.Product;

import java.util.List;

public interface ProductService {
    Product save(ProductDto productDto);
    List<Product> findAll();
    Product findById(String id);
}
