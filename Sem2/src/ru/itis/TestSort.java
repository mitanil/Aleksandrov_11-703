package ru.itis;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class TestSort {
    String fileInput = "Arrays.txt";
    String fileOutput = "Result.txt";

    @Before
    public void start(){
        try {
            Main.sort(fileInput, fileOutput, SortType.MEDIAN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCorrectOutput() throws IOException {
        File arraysResult = new File(fileOutput);
        assertTrue(arraysResult.exists());
        BufferedReader reader = new BufferedReader(new FileReader(arraysResult));
        while (reader.ready()){
            int []array = Main.toIntArray(reader.readLine().split(" "));
            for(int i = 0; i < array.length - 1; i++){
                if(array[i] > array[i+1]){
                    assertTrue(false);
                    return;
                }
            }
        }
        assertTrue(true);
    }
}
