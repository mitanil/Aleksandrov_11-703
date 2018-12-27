package ru.itis;

import java.io.Serializable;

/**
 * 25.04.2018
 * UserIO
 *
 * @author Aleksandrov Andrey
 */
public class User implements Serializable{
    private String login;
    private int password;
    private String name;
    private String surname;

    public User(String login, int password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return  "login='" + login + '\'' + '\n' +
                ", password=" + password + '\n' +
                ", name='" + name + '\'' + '\n' +
                ", surname='" + surname;
    }
}
