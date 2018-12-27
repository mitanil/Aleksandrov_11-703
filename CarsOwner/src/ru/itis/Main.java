package ru.itis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File users = new File("users.txt");
        File cars = new File("cars.txt");
        try {
            if(!users.exists()) {
                users.createNewFile();
            }
            if(!cars.exists()){
                cars.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner s1 = null;
        Scanner s2 = null;
        try {
            s1 = new Scanner(new FileReader(users));
            s2 = new Scanner(new FileReader(cars));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert s1 != null;
        assert s2 != null;
        int[] result = new int[100];
        String[] car = s2.nextLine().split(" ");
        while (s1.hasNext()) {
            int countOfCars = 0;
            String[] user = s1.nextLine().split(" ");
            if (user[0].equals(car[2])){
                int age = Integer.parseInt(user[2]);
                result[age - 1]++;
                while (s2.hasNext()) {
                    car = s2.nextLine().split(" ");
                    if (user[0].equals(car[2])) {
                        result[age-1]++;
                    } else {
                        break;
                    }
                }
            }
        }
        for(int i = 0; i < result.length; i++){
            if(result[i] != 0){
                System.out.println((i + 1) + " " + result[i]);
            }
        }
    }
}
