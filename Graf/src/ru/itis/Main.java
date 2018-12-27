package ru.itis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

//    static Scanner s = null;
//    static private boolean isTree = false;
//
//    static boolean[][] matrix = null;

    public static void main(String[] args) {
//
//        try {
//            s = new Scanner(new FileReader(new File("data.txt")));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        boolean[][] graf = {
                {false, true, true, true, false, false},
                {true, false, false, false, true, true},
                {true, false, false, true, true, false},
                {true, false, true, false, false, false},
                {false, true, true, false, false, true},
                {false, true, false, false, true, false}
        };

        boolean[][] tree = {
                {false, true, false, false, false, true, false, false},
                {true, false, true, true, true, false, false, false},
                {false, true, false, false, false, false, false, false},
                {false, true, false, false, false, false, false, false},
                {false, true, false, false, false, false, false, false},
                {true, false, false, false, false, false, true, false},
                {false, false, false, false, false, true, false, true},
                {false, false, false, false, false, false, true, false}
        };
//
        CheckTree checkTree = new CheckTree();
        checkTree.setMatrix(tree);
        System.out.println(checkTree.checkTree());
//        int[] mass = toIntArray(s.nextLine().split(" "));
//        matrix = new boolean[mass.length][mass.length];
//        for(int i = 0; i < mass.length; i++){
//            matrix[0][i] = !(mass[i] == 0);
//        }
//        for(int f = 1; f < mass.length; f++){
//            mass = toIntArray(s.nextLine().split(" "));
//            for (int i = 0; i < mass.length; i++) {
//                matrix[f][i] = !(mass[i] == 0);
//            }
//        }
//        isTree = s.nextLine().equals("true");

//        CheckTree checkTree = new CheckTree();
//        checkTree.setMatrix(graf);
//        System.out.println(checkTree.checkTree());
//        checkTree.setMatrix(tree);
//        System.out.println(checkTree.checkTree());

    }
//
//    static private int[] toIntArray(String[] symbols) {
//        int[] mass = new int[symbols.length];
//        for(int i = 0; i < symbols.length; i++){
//            mass[i] = Integer.parseInt(symbols[i]);
//        }
//        return mass;
//    }

}