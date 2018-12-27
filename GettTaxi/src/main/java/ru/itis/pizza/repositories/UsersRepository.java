package ru.itis.pizza.repositories;

import ru.itis.pizza.models.User;

/**
 * 03.09.2018
 * UsersRepository
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersRepository extends CrudRepository<User> {
    User findOneByEmail(String email);
}
