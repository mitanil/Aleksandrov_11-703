package ru.itis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 25.04.2018
 * UserIO
 *
 * @author Aleksandrov Andrey
 */
public interface UserRepository {
    void save(User user);
    void deleteByLogin(String login);
    User findByLogin(String login);
    List<User> findAll();
}
