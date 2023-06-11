package com.ecommerce.products.services.interfaces;

import com.ecommerce.products.dtos.OrderItemsDto;
import com.ecommerce.products.dtos.ProductDto;
import com.ecommerce.products.exceptions.ProductException;
import com.ecommerce.products.models.Product;

import java.util.List;

public interface ProductService {
    Product save(ProductDto productDto);
    List<Product> findAll();
    Product findById(String id) throws ProductException;
    Product update(Product product, String id) throws ProductException;
    void deleteById(String id) throws ProductException;
    void subtractProduct(List<OrderItemsDto> products) throws ProductException;
}
