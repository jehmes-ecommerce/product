package com.ecommerce.products.services.interfaces;

import com.ecommerce.products.models.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
    Product findById(String id);
}
