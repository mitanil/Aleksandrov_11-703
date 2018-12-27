package ru.itis.services;

import ru.itis.models.Product;

import java.util.List;

public interface ProductService {
    void getProductById(Long productId);
    List<Product> getAllProducts();
}
