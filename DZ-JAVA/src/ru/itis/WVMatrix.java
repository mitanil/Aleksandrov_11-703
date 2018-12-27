package ru.itis;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Aleksandrov Andrey
 *         11-703
 *         for 037 038 039
 */

public class WVMatrix {
    private static Scanner s = new Scanner(System.in);

    public static int[][] getSquareMatrix() {
        int n;
        System.out.print("Введите n: ");
        n = s.nextInt();
        return getMatrix(n, n);
    }

    public static int[][] getMatrix(int m, int n) {

        Random r = new Random();
        int matrix[][];

        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.out.print("[ ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = r.nextInt(7);
//                matrix[i][j] = 1.1;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("]");
        }
        return matrix;
    }

    public static void printMatrix(int matrix[]) {
        System.out.print("[ ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + " ");
        }
        System.out.println("]");
    }
    public static void printMatrix(int matrix[][]){
        for (int i = 0; i < matrix.length; i++) {
            printMatrix(matrix[i]);
        }
    }
}
