package ru.itis.services;


import ru.itis.models.Product;
import ru.itis.repositories.ProductRepository;
import ru.itis.repositories.ProductRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.List;


public class ProductServiceImpl implements ProductService{

    ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    @Override
    public void getProductById(Long productId) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getProductsList();
    }
}
