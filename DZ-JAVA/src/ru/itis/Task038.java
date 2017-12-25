package ru.itis;

/**
 * @author Aleksandrov Andrey
 *         11-703
 *         038
 */
public class Task038 {
    public static void main(String[] args) {
        int[][] matrix = WVMatrix.getSquareMatrix();
        System.out.println();
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int n = matrix[i][i];
                int t = matrix[j][i];
                for(int f = i; f < matrix.length; f++){
                    matrix[j][f] = matrix[j][f] * n - matrix[i][f] * t;
                }
            }
        }
        WVMatrix.printMatrix(matrix);
    }
}
