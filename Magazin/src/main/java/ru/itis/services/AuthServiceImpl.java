package ru.itis.services;

import ru.itis.models.Auth;
import ru.itis.models.User;
import ru.itis.repositories.AuthRepository;
import ru.itis.repositories.AuthRepositoryJdbcImpl;

import javax.sql.DataSource;

public class AuthServiceImpl implements AuthService {

    AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository){
        this.authRepository = authRepository;
    }


    @Override
    public Auth createSession(User user) {
        return authRepository.createSession(user);
    }
}
