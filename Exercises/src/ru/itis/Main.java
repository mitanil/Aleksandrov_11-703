package ru.itis;

import java.util.Scanner;

public class Main {
    private static Scanner s;


//    Каждое задание реализованно отдельной функцией
//    и для запуска необходимо прописывать вызов нужной функции в главной функции
    public static void main(String[] args) {
        s = new Scanner(System.in);
        e9d();
    }

    //    Упражнение 4 а)
    private static void e4a() {
        System.out.println("Введите n");
        int t = s.nextInt();
        float res = 1.0f;
        int i = 2;
        while (i <= t) {
            res += 1.0f / i;
            i++;
        }
        System.out.println("H = " + res);
    }

    //    упражнение 5 а)
    private static void e5a() {
        System.out.println("Введите n");
        int n = s.nextInt();
        float res = 0.0f;
        int pm = 1;
        int k = 1;
        while (n >= 0) {
            float t = 1.0f / k;
            res += pm*t;
            pm*= -1;
            n--;
            // *
            k += 2;
        }
        System.out.println("Sn = " + res);
    }

    //    Упражнение 6 а)
    private static void e6a() {
        System.out.println("Введите n: ");
        int n = s.nextInt();
        double catalansConstant = 0;
        int i = 1, pm = 1;
        while (i <= n) {
            catalansConstant = addElementOfCatalansConstant(catalansConstant, i, pm);
            pm *= -1;
            i++;
        }
        System.out.println(catalansConstant);
    }

    //    Упражнение 6 б)
    private static void e6b() {
        double e = Math.pow(10, -5);
        int n = 1, pm = 1;
        double catalansConstant = 0;
        while (true) {
            catalansConstant = addElementOfCatalansConstant(catalansConstant, n, pm);
            pm *= -1;
            n++;
            if (catalansConstant <= e) {
                break;
            }
        }
        System.out.println(catalansConstant);
    }

    //    часть задачи 6
    private static double addElementOfCatalansConstant(double halfOfCatalansConstant, int n, int plusMinus) {
        halfOfCatalansConstant += plusMinus / Math.pow((1 + 2 * (n - 1)), 2);
        return halfOfCatalansConstant;
    }

    //    упражнение 9 a)
    private static void e9a() {
        System.out.println("Введите m: ");
        int m = s.nextInt();
        double res = 0;
        int i = 1,
            pm = 1;
        while (i <= m) {
            res += pm / Math.pow(i, 2);
            pm *= -1;
            i++;
        }
        System.out.println(res);

    }

    //    упражнение 9 б)
    private static void e9b() {
        System.out.println("Введите m: ");
        int m = s.nextInt();
        int i = 2;
        int t1 = 1;
        int t2 = 2;
        double res = 0;
        while (i <= m) {
            t1 *= (i - 1);
            t2 *= (2 * i - 1) * 2 * i;
            res += Math.pow(t1, 2) / t2;
            i++;
            System.out.println(t1);
            System.out.println(t2);
            System.out.println(res);
        }
        System.out.println(res);
    }

    //    упражнение 9 в)
    public static void e9c() {
        System.out.println("Введите m: ");
        int m = s.nextInt(),
                i = 1,
                t = 1;
        double res = 1,
                pm = -1;
        while (i <= m) {
            t *= 3;
            res += pm / ((2 * i + 1) * t);
            pm *= -1;
            i++;
        }
        System.out.println(res);
    }

    //    упражнение 9 г)
    private static void e9d() {
        System.out.println("Ввеите m: ");
        int m = s.nextInt(),
                i = 0,
                t = 1;
        double res = 0;
        while (i <= m) {
            res += 1.0 / ((2 * i + 1) * t);
            t *= 9;
            i++;
        }
        System.out.println(res);
    }

}
