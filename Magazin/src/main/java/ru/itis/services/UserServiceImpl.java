package ru.itis.services;


import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.repositories.UserRepositoryJdbcImpl;


public class UserServiceImpl implements UserService{

    UserRepository userRepositiory;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepositiory = userRepository;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String rawPassword) {
        User user = userRepositiory.getUserByLogin(login);
        if( user != null && (new BCryptPasswordEncoder()).matches(rawPassword, user.getHashPassword())){
            return user;
        }
        return null;
    }

    @Override
    public User getUserByUUID(String uuid) {
        return userRepositiory.getUserByUUID(uuid);
    }

    @Override
    public User getUserById(Long user_id) {
        return userRepositiory.getUserById(user_id);
    }

    @Override
    public boolean isExistByCookie(String uuid) {
        return userRepositiory.getUserByUUID(uuid) != null;
    }

    @Override
    public boolean isExistByLogin(String login) {
        return userRepositiory.getUserByLogin(login) != null;
    }

    @Override
    public User createUser(String login, String rawPassword) {
        return userRepositiory.createUser(login, rawPassword);
    }

}
