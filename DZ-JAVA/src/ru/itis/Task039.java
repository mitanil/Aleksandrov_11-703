package ru.itis;

import java.util.Scanner;

/**
 * @author Aleksandrov Andrey
 *         11-703
 *         039
 */

public class Task039 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите размер матрицы: ");
        int[][] matrix = WVMatrix.getMatrix(s.nextInt(), s.nextInt());
        int [] mass = new int[] {214, 337, 231, 21, 32};

        for(int i = 0; i < mass.length; i++){
            for(int f = 1; f < mass[i]; f*= 10){

            }
        }
    }
}
