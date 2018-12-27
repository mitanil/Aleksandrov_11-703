package ru.itis.repositories;

import ru.itis.models.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProductsList();
}
