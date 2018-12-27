package ru.itis.pizza.app;

import ru.itis.pizza.models.User;
import ru.itis.pizza.repositories.UsersRepository;
import ru.itis.pizza.repositories.UsersRepositoryListImpl;
import ru.itis.pizza.services.UsersService;
import ru.itis.pizza.services.UsersServiceImpl;

/**
 * 03.09.2018
 * Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Application {
    public static void main(String[] args) {

        UsersRepository usersRepository = new UsersRepositoryListImpl();
        UsersService usersService = new UsersServiceImpl(usersRepository);

        User user = User.builder()
                .email("sidikov.marsel@gmail.com")
                .firstName("Marsel")
                .lastName("Sidikov")
                .rawPassword("qwerty007")
                .build();

        usersService.register(user);

        user.setRawPassword("qwerty008");

        System.out.println(usersService.isRegistered(user));
        System.out.println(user.getHashPassword());
    }
}
