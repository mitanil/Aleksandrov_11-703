package ru.itis;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * 10.05.2018
 * Graf
 *
 * @author Aleksandrov Andrey
 */
public class TestCheckTree {
    Scanner s = null;
    private boolean isTree = false;

    {
        try {
            s = new Scanner(new FileReader(new File("data.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    boolean[][] matrix = null;

    @Before
    void start() {
        int[] mass = toIntArray(s.nextLine().split(" "));
        matrix = new boolean[mass.length][mass.length];
        for(int i = 0; i < mass.length; i++){
            matrix[0][i] = !(mass[i] == 0);
        }
        String str = s.nextLine();
        int f = 1;
        while (!str.equals("true") && !str.equals("false")) {
            mass = toIntArray(str.split(" "));
            for (int i = 0; i < mass.length; i++) {
                matrix[f][i] = !(mass[i] == 0);
            }
            f++;
        }
        isTree = str.equals("true");
    }

    private int[] toIntArray(String[] symbols) {
        int[] mass = new int[symbols.length];
        for(int i = 0; i < symbols.length; i++){
            mass[i] = Integer.parseInt(symbols[i]);
        }
        return mass;
    }

    @Test
    void testCheckTree(){
        CheckTree checkTree = new CheckTree();
        checkTree.setMatrix(matrix);
        System.out.println(checkTree.checkTree());

        assertTrue(checkTree.checkTree() == isTree);
    }
}
