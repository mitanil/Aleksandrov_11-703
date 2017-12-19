package ru.itis;

import java.util.Scanner;

public class Exercise52 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите две строки");
        String str1 = s.nextLine();
        String str2 = s.nextLine();
        if(str1.length() < str2.length()){
            System.out.println("Вторая строка не входит в первую");
            return;
        }
        for(int i = 0, f = 0; i < str1.length(); i++){
            if(str1.charAt(i) == str2.charAt(f)){
                f++;
                if(f == str2.length()){
                    System.out.println("Вторая строка входит в первую");
                    return;
                }
            } else if (f > str1.length() - i) {
                System.out.println("Вторая строка не входит в первую");
                return;
            }else f = 0;
        }
        System.out.println("Втрорая строка не входит в первую");
    }
}
