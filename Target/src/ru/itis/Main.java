package ru.itis;

import java.io.*;
import java.util.Scanner;

public class Main {

    static BufferedReader s;

    public static void main(String[] args) throws Exception {
        s = new BufferedReader(new FileReader("Data.txt"));
        Circle[] table = createDartsTable();
        Hit[] hits = createHitsArray();
        System.out.println(checkHits(table, hits));
    }

    private static Circle[] createDartsTable() throws IOException {
        Circle[] tables = new Circle[Integer.parseInt(s.readLine())];
        for (int i = 0; i < tables.length; i++) {
            String[] circleInfo = s.readLine().split(" ");
            tables[i] = new Circle(
                    Integer.parseInt(circleInfo[0]),
                    Integer.parseInt(circleInfo[1]));
        }
        return tables;
    }

    private static Hit[] createHitsArray() throws IOException {
        Hit[] hits = new Hit[Integer.parseInt(s.readLine())];
        for (int i = 0; i < hits.length; i++) {
            String[] hitInfo = s.readLine().split(",");
            hits[i] = new Hit(
                    Integer.parseInt(hitInfo[0]),
                    Integer.parseInt(hitInfo[1]));
        }
        return hits;
    }

    static int checkHits(Circle[] table, Hit[] hits){
        int sumPoints = 0;
        for (int i = 0; i < hits.length; i++) {
            int t = 0;
            for (int f = table.length - 1; f >= 0; f--) {
                if(table[f].getRadius() > hits[i].getRadius()){
                    t = table[f].getPoints();
                }else break;
            }
            sumPoints += t;
        }
        return sumPoints;
    }

//
//    static int factorial(int n) {
//        if (n == 1) {
//            return n;
//        } else
//            return n * factorial(n - 1);
//    }
//
//    static void perest(int n) {
//        int count = 0;
//        int mass[] = new int[String.valueOf(n).length()];
//        for (int i = 0; i < mass.length; i++) {
//            mass[i] = n % 10;
//            n = n / 10;
//        }
//        for (int i = mass.length - 1; i >= 0; i--) {
//            for (int f = i - 1; f >= 0; f--) {
//                if (mass[i] < mass[f]) {
//                    count += factorial(i);
//                }
//            }
//        }
//        System.out.println(count);
//    }


}
