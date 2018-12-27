package ru.itis.repositories;

import ru.itis.models.User;

public interface UserRepository {
    User getUserByUUID(String uuid);
    User getUserByLogin(String login);
    User getUserById(Long id);
    User createUser(String login, String password);


}
