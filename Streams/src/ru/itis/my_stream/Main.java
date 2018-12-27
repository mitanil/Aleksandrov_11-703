package ru.itis.my_stream;


import java.io.StringReader;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> intList = new LinkedList<>(new Integer[]{12, 3, 4, 7, 2, 6, 42});
        LinkedList<String> doubleList = intList.stream()
                .filter(integer -> integer > 3)
                .sorted()
                .map(i -> Double.parseDouble(i.toString()))
                .toList();
        System.out.println(doubleList);

    }
}
