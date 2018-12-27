package ru.itis;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class Main {

    private static int countIterations = 0;

    private static ArrayList<Integer> statistic = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        sort("Arrays.txt", "Result.txt", SortType.RANDOM);
        sort("Arrays.txt", "Result.txt", SortType.MEDIAN);
        sort("Arrays.txt", "Result.txt", SortType.MIDDLE);
        sort("Arrays.txt", "Result.txt", SortType.FIRST);

//        int end[] = {'к', 'о', 'н', 'е', 'ц'};
//        sort(end, SortType.FIRST);
//        for(int i = 0; i < end.length; i++){
//            System.out.print((char)end[i]);
//        }
    }

    public static void sort(String fileInput, String fileOutput, SortType sortType) throws IOException {
        countIterations = 0;
        File arrays = new File(fileInput);
        if (!arrays.exists()) {
            System.err.println("Файл с данными не найден!");
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(arrays));
        File result = new File(fileOutput);
        if (!result.exists()) {
            result.createNewFile();
        }
        PrintWriter writer = new PrintWriter(result);
        int[] array;
        while (reader.ready()) {
            array = toIntArray(reader.readLine().split(" "));
            sort(array, sortType);

            for (int i = 0; i < array.length; i++) {
                writer.print(array[i] + " ");
            }
            writer.print("\n");
        }
        writer.close();
        int sum = 0;
        for (Integer temp : statistic)
            sum += temp;
        sum /= statistic.size();
        System.out.println(sum);
        System.out.println("Количество итераций: " + countIterations);
    }

    private static void sort(int[] array, SortType type) {
        long time = System.nanoTime();
        quickSort(array, 0, array.length - 1, type);
        statistic.add((int) (System.nanoTime() - time));
    }

    private static void quickSort(int [] array, int low, int high, SortType type){
        if(low < high){
            int dp = partition(array, low, high, type);
            quickSort(array, low, dp - 1, type);
            quickSort(array, dp + 1, high, type);
        }
    }

    private static int partition(int[] array, int low, int high, SortType type) {
        int i = low;
//        int pivot = array[i];
        swap(array,low, getPivotIndex(array, low, high, type));
        int pivot = array[low];
        for(int j = low + 1; j <= high; j++){
            if(array[j] < pivot){
                i++;
                swap(array, i, j);
            }
            countIterations++;
        }
        swap(array, low, i);
        return i;
    }

    private static int getPivotIndex(int[] array, int low, int high, SortType type) {
        int pivot = 0;
        switch (type){
            case MEDIAN:
                int sum = 0;
                for(int i = low; i < high; i++){
                    sum += array[i];
                }
                int average = sum/ (high - low);
                int medium = low;
                int difference = average;
                for(int i = low; i < high; i++){
                    if (Math.abs(average - array[i]) < difference) {
                        medium = i;
                        difference = Math.abs(average - array[i]);
                    }
                }
                pivot = medium;
                break;
            case MIDDLE:
                pivot = low + (high - low)/2;
                break;
            case RANDOM:
                Random r = new Random();
                pivot = r.nextInt(high - low) + low;
                break;
            case FIRST:
                pivot = low;
                break;
        }
        return pivot;
    }

    private static void swap(int [] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] toIntArray(String[] stringArray) {
        int[] array = new int[stringArray.length];
        for(int i = 0; i < stringArray.length; i++){
            array[i] = Integer.parseInt(stringArray[i]);
        }
        return array;
    }
}
