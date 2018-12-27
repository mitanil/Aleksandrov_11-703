package ru.itis;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        User user1 = new User("user1", "user1".hashCode(), "user", "userevich");
        User user2 = new User("user2", "user1".hashCode(), "user", "userevich");
        User user3 = new User("user3", "user1".hashCode(), "user", "userevich");
        User user4 = new User("user4", "user1".hashCode(), "user", "userevich");
        User user5 = new User("user5", "user1".hashCode(), "user", "userevich");
        User user6 = new User("user6", "user1".hashCode(), "user", "userevich");
        UserRepository ioStream1 = new UserIOStream("users.txt");
        UserRepository ioStream2 = new UserDataIOStream("users.data");
//        ioStream1.save(user1);
//        ioStream1.save(user2);
//        ioStream1.save(user3);
//        ioStream1.save(user4);
//        ioStream1.save(user5);
//        ioStream1.save(user6);

//        ioStream2.save(user1);
//        ioStream2.save(user2);
//        ioStream2.save(user3);
//        ioStream2.save(user4);
//        ioStream2.save(user5);
//        ioStream2.save(user6);
        List<User> users = ioStream2.findAll();
        for(User t: users){
            System.out.println(t);
            System.out.println();
        }
    }
}
