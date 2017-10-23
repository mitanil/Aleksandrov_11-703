package ru.itis;

import java.util.Scanner;

public class Main {
    private static Scanner s;


//    Каждое задание реализованно отдельной функцией
//    и для запуска необходимо прописывать вызов нужной функции в главной функции
    public static void main(String[] args) {
        s = new Scanner(System.in);
        e12c();
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
        while (k <= n) {
            float t = 1.0f / (2*k - 1);
            res += pm*t;
            pm*= -1;
            k++;
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
        System.out.println("Введите n: ");
        int m = s.nextInt();
        int i = 2;
        double res = 0, t = 0.5;
        while (i <= m) {
            t *= Math.pow(i - 1, 2) / (2 * i - 1) * 2 * i;
            res += t;
            i++;
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

//    Упражнение 10 а)
    private static void e10a(){
        int x, tx, tk, k;
        double res, t;
        System.out.println("Введите k и x: ");
        k = s.nextInt();
        x = s.nextInt();
        t = x;
        res = t;
        tx = -1*x*x*x;
        tk = 1;
        int i = 1;
        while (i<= k) {
            t *= (double)(-1 * x * x) / (i * (2 * i + 1));
            res += t;
            i++;
        }
        System.out.println(res);
    }
//    Упражнение 10 б)
    private static void e10b(){
        int x, k, tpx;
        double res, t;
        System.out.println("Введите k и x: ");
        k = s.nextInt();
        x = s.nextInt();
        t = x;
        res = t;
        tpx = -1 * (int)Math.pow(x, 4);
        int i = 1;
        while (i<=k) {
            t *= (double)tpx / (2 * i * (2 * i - 1) * (4 * i - 1));
            res += t;
            i++;
        }
        System.out.println(res);
    }

    //    Упражнение 10 в)
    private static void e10c(){
        int x, k;
        System.out.println("Введите n и x: ");
        double res = 0, t = 1;
        k = s.nextInt();
        x = s.nextInt();
        int i = 1;
        while (i <= k) {
            t *= (double) (x * x * (2 * i - 1)) / (2 * i);
            res += t;
            i++;
        }
        System.out.println(res);
    }

    //    Упражнение 10 г)
    private static void e10d(){
        System.out.println("Введите n и x: ");
        int n = 2 * s.nextInt();
        int x = s.nextInt();
        double res = 0, t1 = 1, t2 = 1;
        int i = 1;
        boolean isEven = false;
        while (i<=n) {
            t1 *= x;
            t2 *= x;
            if(isEven){
                t1 /= i;
                res += t1;
            }else{
                t2 /= i;
                res += t2;
            }
            i++;
            isEven = !isEven;
        }
        System.out.println(res);
    }

    //    Упражнение 5 б)
    private static void e5b(){
        final double E = 0.0001;
        final double PI = 3.1415926;
        final double Sn = PI/4;
        double res = 1;
        int pm = -1;
        int n = 2;
        System.out.println("Pi/4 = " + Sn);
        while (Math.abs(Sn - res) >= E) {
            res += (double) pm / (2 * n - 1);
            n++;
            pm *= -1;
        }
        System.out.println("Sn = " + res);
        System.out.println(n);
    }

    //    Упражнение 7
    private static void e7(){
        final double E = 0.001;
        final double PI = 3.1415926;
        double res = 1;
        final double Rn = PI/2;
        int n = 1;
        System.out.println("E = " + E);
        System.out.println("Pi/4 = " + Rn);
        while (Math.abs(res - Rn) >= E) {
            res *= (double) (4 * n * n) / (4 * n * n - 1);
            n++;
        }
        System.out.println("Rn = " + res);
        System.out.println(n);
    }

    //    Упражнение 6 б)
    private static void e6b(){
        final double E = 0.0001;
        double res = 0, t = 1;
        int n = 1, pm = 1;
        while (Math.abs(t) > E) {
            res += t;
            t = (double) pm / ((2 * n - 1) * (2 * n - 1));
            n++;
            pm *= -1;
        }
        System.out.println("G = " + res);
    }

    //    Упражнение 12 а)
    private static void e12a(){
        final double E = 0.0001;
        System.out.println("Введите x: ");
        int n = 1, x = s.nextInt();
        double res = 0, t = 1;
        while (t >= E) {
            res += t;
            t *= (double) x / n;
            n++;
        }
        System.out.println(res);
    }

    //    Упражнение 12 б)
    private static void e12b(){
        final double E = 0.0001;
        System.out.println("Ведите х: ");
        int x = s.nextInt(), n = 1, pm = -1;
        double res = 0, t = x;
        while (Math.abs(t) >= E) {
            res += t;
            t *= (double) (x * x) / ((2 * n + 1) * (2 * n));
            n++;
            pm *= -1;
        }
        System.out.println(res);
    }

    //    Упражнение 12 в)
    private static void e12c(){
        final double E = 0.0001;
        System.out.println("Ведите х: ");
        int  n = 2, pm = 1;
        double x = s.nextDouble(), res = 0, t = (double)x/n;
        while (Math.abs(t / n) > E) {
            res += t / n;
            t *= (double) pm * x;
            n++;
            pm *= -1;
        }

        System.out.println(res);
    }

    //    Упражнение 12 г)
    private static void e12d(){
        final double E = 0.0001;
        System.out.println("Dведите x: ");
        int x = s.nextInt(), n = 0, pm = -1;
        double res = 0, t = pm*x*x/2;
        while (Math.abs(t)>E){
            res += t;
            n++;
            pm *= -1;
            t *= x*x/((n+1)* (n+2));
        }
        System.out.println(res);
    }

    //    Упражнение 11 б)
    private static void e11b(){
        System.out.println("Введите n: ");
        int i = 0,
            n = s.nextInt();
        double res = 0,
            t = 1;
        while(i<=n){
            t *= Math.cos(i)/Math.sin(i);
            res += t;
            i++;
        }
        System.out.println(res);
    }

    //    Упражнение 11 г)
    private static void e11d(){

        System.out.println("Введите n: ");
        int i = 1,
                n = s.nextInt();
        double res = 0,
                t = 1;
        while(i<=n){
            t *= Math.sin(i)/Math.cos(i);
            res *= t;
            i++;
        }
        System.out.println(res);
    }

    //    Упражнение 14 a)
    private static void e14a(){
        System.out.println("Введите x: ");
        final double E = 0.0001;
        int k = 1,
            pm = -1,
            x = s.nextInt();
        double res = 0, t = 1;
        while(t>E){
            res += pm * t * Math.pow(Math.sin(t * x), 2);
            k++;
            t /= k;
            pm *= -1;
        }
    }

    //    Упражнение 14 б)
    private static void e14b(){
        final double E = 0.0001;
        System.out.println("Введите x и а > 1: ");
        int x = s.nextInt(),
            a = s.nextInt();
        if(a<=1){
            System.out.println("a <= 1");
            return;
        }
        double res = 0,
            t = 1/a;
        while(t>E){
            res += t * Math.log10(t * x);
            t /= a;
        }
    }

    //    Упражнение 24 a)
    private static void e24a(){
        System.out.println("Введите m и x: ");
        final double E = 0.0001;
        int m = s.nextInt(), x = s.nextInt();
        int n = 1;
        double res = 0, t = 1;
        while (n<=m && (Math.sin(n*x)/t)>=E){
            res += Math.sin(n*x)/t;
            n++;
            t *= n;
        }
        System.out.println(res);
    }

    //    Упражнение 24 б)
    private static void e24b(){
        System.out.println("Введите m и x, при 0 < x < pi/2: ");
        int m = s.nextInt(), x = s.nextInt(), n = 1, pm = 1;
        final double E = 0.0001;
        if(x <= 0 || x >= Math.PI/4 ){
            System.out.println("x<=0 или x >= pi/4");
            return;
        }
        double res = 0;
        while(n<=m && (Math.cos(2*n - 1) * x/n)>=E){
            res += pm * Math.cos(2*n - 1) * x/n;
            pm*= -1;
        }
        System.out.println(res);
    }

    
    //    Упражнение 23 a)
    private static void e23a(){
        System.out.println("Введите a, b и x, при 0 < a < b: ");
        int a = s.nextInt(),
            b = s.nextInt(),
            x = s.nextInt(),
            t1 = a,
            t2 = a,
            vk = t1 + t2;
        if(a <= 0 || a >= b){
            System.out.println("a <= 0 или a >= b");
            return;
        }
        double res = Math.log10(t1*x);
        while (vk <= b){
            res += Math.log10(vk*x);
            t1 = t2;
            t2 = vk;
            vk = t1 + t2;
        }
        System.out.println(res);
    }

    //    Упражнение 23 б)
    private static void e23b(){
        System.out.println("Введите a, b и x, при 0 < a < b: ");
        int a = s.nextInt(),
            b = s.nextInt(),
            x = s.nextInt(),
            t1 = a,
            t2 = a,
            t3 = (int) Math.pow(2, a),
            vk = t1 + t2,
            pm = -1;
        if(a <= 0 || a >= b){
            System.out.println("a <= 0 или a >= b");
            return;
        }
        if(a%2 == 0)pm = 1;
        double res = pm * t1/t3*Math.pow(Math.cos(t1*x), 2);
        while(vk <= b){
            t3 *= 2;
            res = pm * vk/t3*Math.pow(Math.cos(vk*x), 2);
            t1 = t2;
            t2 = vk;
            vk = t1 + t2;
        }
        System.out.println(res);
    }

    //    Упражнение 29 б)
    private static void e29b(){
        final double E = 0.0001;
        int n = 2;
        double  t1 = Math.cos(0),
                t2 = Math.cos(t1);
        while(Math.abs(t1*t2)> E){
            t1 = t2;
            t2 = Math.cos(t1);
            n++;
        }
        System.out.println(n);
    }


    //    Упражнение 29 a)
    private static void e29a(){
        final double E = 0.0001;
        int n = 2;
        double  t1 = 0.5,
                t2 = (t1+1)/(t1+2);
        while(Math.abs(t1*t2) > E){
            t1 = t2;
            t2 = (t1+1)/(t1+2);
            n++;
        }
        System.out.println(n);
    }

    //    Упражнение 27
    private static void e27(){
        final double E = 0.0001;
        System.out.println("Введите x при 0 < x < 2: ");
        double  x = s.nextDouble(),
                a = 1,
                b = 1 - x;
        while(Math.abs(b)>E){
            a = a*(1 + b);
            b = b*b;
        }
        System.out.println(a);
    }

    //    Упражнение 28
    private static void e28(){

        final double E = 0.0001;
        System.out.println("Введите x при 0 < x < 2: ");
        double  x = s.nextDouble(),
                a = x,
                b = 1.0 - x;
        if(x <= 0 || x >= 2){
            System.out.println("x <= 0 или x >= 2");
            return;
        }
        while(Math.abs(b)>E){
            a = a * (1.0 + b/2);
            b = b * b * (3.0 + b);
        }
        System.out.println(a);
        System.out.println(a);
    }

    //    Упражнение 25
    private static void e25(){
        System.out.println("Введите x: ");
        final double E = 0.0001;
        double x = s.nextDouble(), t, y;
        if(x >= 1)
            t = 1.0/3.0;
        else
            t = x;
        y = t - 1.0/3.0*(t - x/(t*t));
        while(Math.abs(y - t) > E){
            t = y;
            y = t - 1.0/3.0*(t - x/(t*t));
        }
        System.out.println(y);
    }

    //    Упражнение 30 а)
    private static void e30a() {
        final double E = 0.0001;
        double x = 1.1, xn, f, fsh;
        f = Math.pow(x, 5) - x - 0.002;
        fsh = Math.pow(x, 4) * 5 - 1;
        xn = x - f/fsh;
        while (Math.abs(xn - x) > E){
            x = xn;
            f = Math.pow(x, 5) - x - 0.002;
            fsh = Math.pow(x, 4) * 5 - 1;
            xn = x - f/fsh;
        }
        System.out.println(f);
    }

    //    Упражнение 32 б)
    private static void e32b(){
        System.out.println("Введите a: ");
        final double E = 0.0001;
        double  x = s.nextDouble(),
                xn = 1.0 + 9.0*Math.sin(x)/2.0;
        while(Math.abs(xn - x) > E){
            x = xn;
            xn = 1.0 + 9.0*Math.sin(x)/2.0;
        }
        System.out.println(xn);

    }

    //    Упражнение 20 д)
    private static void e20d(){
        System.out.println("Введите n и 8 переменных: ");
        int x0, x1, x2, xk = 0, y1, y2, yk = 0, n = s.nextInt(), i = 3, t1, t2, t3, t4, t5;
        x0 = s.nextInt();
        x1 = y1 = s.nextInt();
        x2 = y2 = s.nextInt();
        t1 = s.nextInt();
        t2 = s.nextInt();
        t3 = s.nextInt();
        t4 = s.nextInt();
        t5 = s.nextInt();
        while (i <= n) {
            xk = x2 * t1 + y1 * t2 + x0 * t3;
            yk = x2 * t4 + y1 * t5;
            x0 = x1;
            x1 = x2;
            x2 = xk;
            y1 = y2;
            y2 = yk;
            i++;
        }
        System.out.println("x = " + xk + " y = " + yk);
    }
}
