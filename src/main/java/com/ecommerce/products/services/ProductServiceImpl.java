package com.ecommerce.products.services;

import com.ecommerce.products.dtos.OrderItemsDto;
import com.ecommerce.products.dtos.ProductDto;
import com.ecommerce.products.enums.ProductExceptionMessage;
import com.ecommerce.products.exceptions.ProductException;
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
    public Product findById(String id) throws ProductException {
        return productRepository.findById(id).orElseThrow(() ->  new ProductException(ProductExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public void deleteById(String id) throws ProductException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductException(ProductExceptionMessage.NOT_FOUND.getMessage()));
        productRepository.delete(product);
    }

    @Override
    public Product update(Product product, String id) throws ProductException {
        Product productFromDb = productRepository.findById(id).orElseThrow(() -> new ProductException(ProductExceptionMessage.NOT_FOUND.getMessage()));
        productFromDb.setName(product.getName());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setQuantity(product.getQuantity());
        return productRepository.save(productFromDb);
    }

    @Override
    public void subtractProduct(List<OrderItemsDto> products) throws ProductException {
        for (OrderItemsDto product : products) {
            Product productFromDb = findById(product.productId());
            if (productFromDb.getQuantity() - product.quantity() < 0) {
                throw new ProductException(ProductExceptionMessage.OUT_OF_BOUNDS.getMessage());
            }
            productFromDb.setQuantity(productFromDb.getQuantity() - product.quantity());
            update(productFromDb, productFromDb.getProductId());
        }
    }
}
