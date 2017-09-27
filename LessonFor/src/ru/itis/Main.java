package ru.itis;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner s  = new Scanner(System.in);
        System.out.print("Введите два числа: ");
        int a = s.nextInt(), b = s.nextInt();
//        for(int i = a; i <= b; i++){
//            System.out.print(i + " ");
//        }
        System.out.print("Числа между заданными числами: ");
        for(;a<=b; a++) System.out.print(a + " ");
    }
}
