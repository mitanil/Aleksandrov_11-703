package ru.itis;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 25.04.2018
 * UserIO
 *
 * @author Aleksandrov Andrey
 */
public class UserIOStream implements UserRepository{

    private String fileName;

    public UserIOStream(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try {
            String userData = user.getLogin() + " " + user.getPassword() + " " + user.getName() + " " + user.getSurname() + '\n';
            InputStream input = new FileInputStream(fileName);
            Path filePath = Paths.get(fileName);
            long fileSize = Files.size(filePath);
            byte[] bytes = new byte[(int) fileSize];
            input.read(bytes);
            OutputStream output = new FileOutputStream(fileName);
            output.write(bytes);
            output.write(userData.getBytes());
            output.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByLogin(String login) {
//        byte[] userLogin = login.getBytes();
//        byte[] bytes;
//        try {
//            bytes = new byte[(int) Files.size(Paths.get(fileName))];
//            InputStream input = new FileInputStream(fileName);
//            input.read(bytes);
//            String[] stings = new String(bytes).split("\n");
//            int pastLength = 0;
//            boolean isLogin = true;
//            boolean isLoginFounded = false;
//            boolean isContinue = true;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            String[] strings;
            {
                byte[] bytes = new byte[(int) Files.size(Paths.get(fileName))];
                InputStream input = new FileInputStream(fileName);
                input.read(bytes);
                strings = new String(bytes).split("\n");
            }
            OutputStream output = new FileOutputStream(fileName);
            for(String t: strings){
                String[] user = t.split(" ");
                if(!user[0].equals(login)){
                    output.write((t + '\n').getBytes());
                }
            }
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByLogin(String login) {
        try {
            String[] strings;
            {
                byte[] bytes = new byte[(int) Files.size(Paths.get(fileName))];
                InputStream input = new FileInputStream(fileName);
                input.read(bytes);
                strings = new String(bytes).split("\n");
            }
            for(String t: strings){
                String[] user = t.split(" ");
                if(user[0].equals(login)){
                    return new User(user[0], Integer.parseInt(user[1]), user[2], user[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> users = null;
        try {
            String[] strings;
            {
                byte[] bytes = new byte[(int) Files.size(Paths.get(fileName))];
                InputStream input = new FileInputStream(fileName);
                input.read(bytes);
                strings = new String(bytes).split("\n");
            }
            users = new ArrayList<>(strings.length);
            for (String t : strings) {
                String[] user = t.split(" ");
                users.add(new User(user[0], Integer.parseInt(user[1]), user[2], user[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
