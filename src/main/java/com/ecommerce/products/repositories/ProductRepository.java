package com.ecommerce.products.repositories;

import com.ecommerce.products.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
