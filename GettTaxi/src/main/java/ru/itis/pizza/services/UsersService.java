package ru.itis.pizza.services;

import ru.itis.pizza.models.User;

/**
 * 03.09.2018
 * UsersService
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersService {
    void register(User user);
    boolean isRegistered(User user);
}
