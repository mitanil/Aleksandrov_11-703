package ru.itis;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class CreateArrays {

    public static final int ARRAYS_SIZE = 10000;


    public static void main(String[] args) throws IOException {
        /*
        * Создаем файл, записываем в него наши массивы
        * */
        Random r = new Random();
        File file = new File("Arrays.txt");
        if(!file.exists()) {
            file.createNewFile();
        }
        PrintWriter writer = new PrintWriter(file);
//        int numOfArrays = r.nextInt(50) + 50;
        int numOfArrays = 100;
        for (int i = 0; i < numOfArrays; i++) {
//            int ARRAY_SIZE = r.nextInt(10000) + 100;
            for (int j = 0; j < ARRAYS_SIZE; j++) {
                writer.print(r.nextInt(10000) + " ");
            }
            writer.print("\n");
        }
        writer.close();
    }

}
