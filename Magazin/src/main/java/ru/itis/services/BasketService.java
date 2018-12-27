package ru.itis.services;

import ru.itis.forms.ProductGroup;
import ru.itis.models.Basket;
import ru.itis.models.Product;
import ru.itis.models.User;

import java.util.List;

public interface BasketService {
    List<Product> getProductsByBasketId(Long basketId);
    void addProduct(Long productId, Long basketId);
    Long getBasketIdByUserId(Long userId);
    Basket createBasket(Long userId);

    List<Product> getProductsByUser(User user);

    List<ProductGroup> getGroupedProducts(Long basketId);

    List<ProductGroup> getProductsByUserUuid(String uuid);
}
