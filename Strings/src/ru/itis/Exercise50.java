package ru.itis;

import java.util.Scanner;

public class Exercise50 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите две строки");
        String str1 = s.nextLine();
        String str2 = s.nextLine();
        if(str1.length() != str2.length()){
            System.out.println("Строки не равны");
            return;
        }
        for(int i = 0; i < str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)){
                System.out.println("Строки не равны");
                return;
            }
        }
        System.out.println("Строки равны");
    }
}
