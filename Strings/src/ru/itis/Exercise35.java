package ru.itis;

import java.util.Scanner;

public class Exercise35 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Вводите числа через пробел. Для прекращения ввода введите \"End!\"");
        String number = s.next();
         int min = Integer.MAX_VALUE, count = 0;
        while (!number.equals("End!")){
            count = 0;
            for(int i = 0; i < number.length(); i++){
                if(number.charAt(i) >= '0' && number.charAt(i)<= '9'){
                    count++;
                }else {
                    count = 0;
                    break;
                }
            }
            System.out.println("Для числа " + number);
            if(count == 0){
                System.out.println("Некорректный ввод! В числе присутствует буква или иной символ!\n");
            }else {
                if (min > count) {
                    min = count;
                }
                System.out.println("Длина составляет " + count + "\n");
            }
            number = s.next();
        }
        System.out.println("Минимальная длина числа = " + min);
    }
}
