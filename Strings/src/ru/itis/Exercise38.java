package ru.itis;

import java.util.Scanner;

public class Exercise38 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String number = s.next();
        boolean isContain = false;
        for(int i = 0; i < number.length(); i++){
            if(number.charAt(i) > '0' && number.charAt(i)<= '9') {
                if((number.charAt(i) - '0') % 2 == 0 || (number.charAt(i) - '0') % 3 == 0){
                    isContain = true;
                }
            }else if(number.charAt(i) != '0') {
                System.out.println("Некорректный ввод! В числе присутствует буква или иной символ!\n");
                return;
            }
        }
        if(isContain)
            System.out.println("Число содержит цифры, делимые на 2 или 3");
        else
            System.out.println("Число не содержит цифры, делимые на 2 или 3");

    }
}
