package com.ecommerce.products.services;

import com.ecommerce.products.dtos.ProductDto;
import com.ecommerce.products.models.Product;
import com.ecommerce.products.repositories.ProductRepository;
import com.ecommerce.products.services.interfaces.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(ProductDto productDto) {
        var newProduct = new Product();
        BeanUtils.copyProperties(productDto, newProduct);
        return productRepository.save(newProduct);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
