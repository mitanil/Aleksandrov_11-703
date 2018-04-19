package ru.itis.my_stream;


import java.io.StringReader;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> intList = new LinkedList<>(new Integer[]{12, 3, 4, 7, 2, 6, 42});
        LinkedList<Double> doubleList = intList.stream()
                .filter(integer -> integer > 3)
//                .sorted()
                .map(i -> i.toString())
                .toList();
//        for(String t: doubleList){
//            System.out.println(t.getClass());
//        }
        System.out.println(doubleList);

    }
}
