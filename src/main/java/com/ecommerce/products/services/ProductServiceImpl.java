package com.ecommerce.products.services;

import com.ecommerce.products.dtos.ProductDto;
import com.ecommerce.products.exceptions.NotFoundException;
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

    @Override
    public void deleteById(String id) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product Not Found"));
        productRepository.delete(product);
    }

    @Override
    public Product update(Product product, String id) throws NotFoundException {
        Product productFromDb = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product Not Found"));
        productFromDb.setName(product.getName());
        productFromDb.setPrice(product.getPrice());
        return productRepository.save(productFromDb);
    }
}
