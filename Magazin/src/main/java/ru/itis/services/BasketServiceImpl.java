package ru.itis.services;


import lombok.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.forms.ProductGroup;
import ru.itis.models.Basket;
import ru.itis.models.Product;
import ru.itis.models.User;
import ru.itis.repositories.BasketRepository;
import ru.itis.repositories.BasketRepositoryJdbcImpl;
import ru.itis.repositories.UserRepository;

import java.util.List;

public class BasketServiceImpl implements BasketService{

    BasketRepository basketRepository;
    UserRepository userRepository;

    public BasketServiceImpl(BasketRepository basketRepository, UserRepository userRepository) {
        this.basketRepository = basketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Product> getProductsByBasketId(Long basketId) {
        return null;
    }

    @Override
    public void addProduct(Long productId, Long basketId) {
        basketRepository.addProduct(productId, basketId);
    }

    @Override
    public Long getBasketIdByUserId(Long userId) {
        return basketRepository.getBasketByUser(User.builder().userId(userId).build()).getId();
    }

    @Override
    public Basket createBasket(Long userId) {
        return basketRepository.createBasket(User.builder().userId(userId).build());
    }

    @Override
    public List<Product> getProductsByUser(User user) {
        return basketRepository.getProductsInBasket(basketRepository.getBasketByUser(user));
    }

    @Override
    public List<ProductGroup> getGroupedProducts(Long basketId) {
        return basketRepository.getGroupsProducts(basketId);
    }

    @Override
    public List<ProductGroup> getProductsByUserUuid(String uuid) {
        return getGroupedProducts(getBasketIdByUserId(userRepository.getUserByUUID(uuid).getUserId()));
    }
}
