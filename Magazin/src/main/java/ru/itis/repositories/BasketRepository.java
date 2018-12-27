package ru.itis.repositories;

import ru.itis.forms.ProductGroup;
import ru.itis.models.Basket;
import ru.itis.models.Product;
import ru.itis.models.User;

import java.util.List;

public interface BasketRepository {
    Basket createBasket(User user);
    List<Product> getProductsInBasket(Basket basket);
    Basket getBasketByUser(User user);
    Basket getBasketById(String basketId);
    boolean addProduct(Long productId, Long basketId);
    List<ProductGroup> getGroupsProducts(Long basketId);
}
