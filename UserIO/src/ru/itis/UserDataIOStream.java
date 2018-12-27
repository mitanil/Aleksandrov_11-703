package ru.itis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 26.04.2018
 * UserIO
 *
 * @author Aleksandrov Andrey
 */
public class UserDataIOStream implements UserRepository{

    String fileName = "";

    public UserDataIOStream(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user){
        try {
            List<User> users = findAll();
            if(users == null){
                users = new ArrayList<User>();
            }
            ObjectOutputStream output = null;
            users.add(user);
            output = new ObjectOutputStream(new FileOutputStream(fileName));
            output.writeObject(users);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteByLogin(String login) {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
            List<User> users = findAll();
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
            for (User t : users) {
                if (t.getLogin().equals(login)) {
                    users.remove(t);
                }
            }
            output.writeObject(users);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByLogin(String login) {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
            List<User> users = findAll();
            for(User t: users){
                if(t.getLogin().equals(login))
                    return t;
            }
         } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        try {
            return (List<User>) new ObjectInputStream(new FileInputStream(fileName)).readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}