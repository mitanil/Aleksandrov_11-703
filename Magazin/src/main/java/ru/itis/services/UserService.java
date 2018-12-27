package ru.itis.services;

import ru.itis.models.User;

public interface UserService {
    User getUserByLoginAndPassword(String login, String rawPassword);

    User getUserByUUID(String uuid);
    boolean isExistByCookie(String uuid);

    User getUserById(Long user_id);
    boolean isExistByLogin(String login);

    User createUser(String login, String rawPassword);


}
