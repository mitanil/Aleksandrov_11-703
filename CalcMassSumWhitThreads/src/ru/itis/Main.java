package ru.itis;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] array = getRandomArray(s.nextInt());
        final int sum = getSum(array, s.nextInt());

    }

    public static int getSum(int[] array, int countThread) {
        Thread[] threads = new Thread[countThread];
        int sums[] = {0};
        int partSize = array.length / countThread;
        int partBegin = 0;
        int partEnd = 0;
        int i = 0;
        while (partBegin < array.length){
            partEnd = partBegin + partSize;
            if (partBegin >= array.length){
                break;
            }
            if(partEnd >= array.length || i == countThread-1)
                partEnd = array.length;

            int finalPartEnd = partEnd;
            int finalPartBegin = partBegin;
            Thread t = new Thread(() -> {
                int sum = 0;
                for (int f = finalPartBegin; f < finalPartEnd; f++) {
                    sum += array[f];
                }
                synchronized (sums) {
                    sums[0] += sum;
                }
            });
            threads[i] = t;
            t.start();
            partBegin = partEnd;
            i++;
        }

        for(Thread t: threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sums[0];
    }


    public static int[] getRandomArray(int length){
        int[] array = new int[length];
        Random r = new Random();
        for(int i = 0; i< array.length; i++){
            array[i] = r.nextInt(1000);
        }
        return array;
    }
}
