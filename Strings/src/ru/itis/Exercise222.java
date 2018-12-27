package ru.itis;

import java.util.Scanner;

public class Exercise222 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите строку, вводя слова через пробел и окончив строку словом End!");
        String str = s.next();
        int count = 0;
        while (!str.equals("End!")) {
            boolean isPalindrom = true;
            for(int i = 0; i < str.length()/2; i++){
                if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                    isPalindrom = false;
                    break;
                }
            }
            if(isPalindrom)
                count++;
            str = s.next();
        }
        System.out.println("Количество полиндромов = " + count);
    }
}
