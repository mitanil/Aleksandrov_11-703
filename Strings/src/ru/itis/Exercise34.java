package ru.itis;

import java.util.Scanner;

public class Exercise34 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String number = s.next();
        char max = '0';
        for(int i = 0; i < number.length(); i++){
            if (max < number.charAt(i) && number.charAt(i) <= '9') {
                max = number.charAt(i);
            }
        }
        System.out.println("Максимальная цифра в числе: " + max);
    }
}
