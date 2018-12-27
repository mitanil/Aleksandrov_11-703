package ru.itis.pizza.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.pizza.models.User;
import ru.itis.pizza.repositories.UsersRepository;

/**
 * 03.09.2018
 * UsersServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void register(User user) {
        user.setHashPassword(passwordEncoder.encode(user.getRawPassword()));
        user.setRawPassword(null);
        usersRepository.save(user);
    }

    @Override
    public boolean isRegistered(User user) {
        User existedUser = usersRepository.findOneByEmail(user.getEmail());
        if (existedUser != null) {
            if (passwordEncoder.matches(user.getRawPassword(),
                    existedUser.getHashPassword())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
