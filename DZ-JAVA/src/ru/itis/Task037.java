package ru.itis;

/**
 * @author Aleksandrov Andrey
 *         11-703
 *         037
 */

public class Task037 {
    public static void main(String[] args) {
        int[][] matrix = WVMatrix.getSquareMatrix();
        int n = matrix.length / 2 + matrix.length % 2;
        System.out.println();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                matrix[i][j] =
                        matrix[matrix.length - i - 1][j] =
                        matrix[i][matrix.length - 1 - j] =
                        matrix[matrix.length - i - 1][matrix.length - j - 1] = 0;
            }
        }
        WVMatrix.printMatrix(matrix);
    }
}
