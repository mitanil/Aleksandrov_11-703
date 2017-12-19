package ru.itis;

import java.util.Scanner;

public class Exercise214 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите строку, вводя слова через пробел и окончив строку словом End!");
        String str = s.next();
        while (!str.equals("End!")) {
            System.out.print("В слове " + str + "  превая и последняя буква ");
            if (str.charAt(0) != str.charAt(str.length() - 1)) {
                System.out.print("не ");
            }
            System.out.println("равны!\n");
            str = s.next();
        }
    }
}
