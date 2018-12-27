package ru.itis.my_stream;



public class Sort {

    static<T extends Comparable<T>> T[] sort(T[] array) {
        long time = System.nanoTime();
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private static<T extends Comparable<T>> void quickSort(T[] array, int low, int high){
        if(low < high){
            int dp = partition(array, low, high);
            quickSort(array, low, dp - 1);
            quickSort(array, dp + 1, high);
        }
    }

    private static<T extends Comparable<T>> int partition(T[] array, int low, int high) {
        int i = low;
        T pivot = array[i];
        for(int j = low + 1; j <= high; j++){
            if(array[j].compareTo(pivot) < 0){
                i++;
                swap(array, i, j);
            }
        }
        swap(array, low, i);
        return i;
    }

    private static<T extends Comparable<T>> void swap(T [] array, int i, int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
