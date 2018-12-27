package ru.itis.gett_taxi.repositories;

import ru.itis.gett_taxi.models.User;
import ru.itis.gett_taxi.services.ConnectDB;

public class UsersRepositoryImpl implements UsersRepository {

    ConnectDB connectDB;

    private User user;

    public UsersRepositoryImpl(ConnectDB c){
        connectDB = c;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
