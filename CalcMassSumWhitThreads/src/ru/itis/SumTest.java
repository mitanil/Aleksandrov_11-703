package ru.itis;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * 19.04.2018
 * CalcMassSumWhitThreads
 *
 * @author Aleksandrov Andrey
 */
public class SumTest {
    File data = null;
    @Before
    public void start() {
        data = new File("data.txt");
        if(!data.exists()){
            try {
                data.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Random r = new Random();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 1000; i++){
            String t = "";
            t += r.nextInt(100000) + " " + (r.nextInt(50) + 1) + "\n";
            try {
                fileWriter.write(t);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSum(){
        int[] array = null;
        Scanner s = null;
        try {
            s = new Scanner(new FileReader(data));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (s.hasNext()){
            array = Main.getRandomArray(s.nextInt());
            int checkSum = 0;
            for(int t: array){
                checkSum += t;
            }
            assertTrue(checkSum == Main.getSum(array, s.nextInt()));
        }

    }
}
