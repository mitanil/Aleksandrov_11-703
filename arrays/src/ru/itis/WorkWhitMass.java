package ru.itis;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WorkWhitMass {
    public static int[] createMass(){
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        System.out.print("Введите количество эллементов массива: ");
        int n = s.nextInt(),
                min,
                count = 1;
        int mass[] = new int[n];
        for (int i = 0; i < n; i++) {
            mass[i] = r.nextInt(10);
        }
        System.out.println(Arrays.toString(mass));
        return mass;
    }
}
