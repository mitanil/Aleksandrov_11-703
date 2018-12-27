package ru.itis.gett_taxi.repositories;

import ru.itis.gett_taxi.models.User;

public interface UsersRepository {
    public void setUser(User user);
    public User getUser();

    boolean register();

    boolean enter();
}
