package ru.itis.pizza.repositories;

import ru.itis.pizza.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 03.09.2018
 * UsersRepositoryListImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryListImpl implements UsersRepository {

    private List<User> users = new ArrayList<>();

    @Override
    public User findOneByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void save(User model) {
        users.add(model);
    }

    @Override
    public User find(Long id) {
        return null;
    }
}
