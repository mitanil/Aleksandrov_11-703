package ru.itis;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
//	    IsUpperPredicateImpl predicate = new IsUpperPredicateImpl();
//
//        System.out.println(predicate.test("Marsel"));
//        System.out.println(predicate.test("car"));
//
//        Predicate<String> isUpperPredicate =
//                string -> Character.isUpperCase(string.charAt(0));
//
//        System.out.println(isUpperPredicate.test("Hello"));
//
//        System.out.println("-------");

        List<String> strings = new ArrayList<>();
        strings.add("Marselk");
        strings.add("3Hello");
        strings.add("2Alpha");
        strings.add("Beta");

//        StringsAnalyzer analyzer = new StringsAnalyzer();
//
//        analyzer.analyze(strings, string -> string.length() % 2 == 0);
//        System.out.println("-------");
//        analyzer.analyze(strings, string -> Character.isDigit(string.charAt(0)));

//        Stream<String> stringsStream = strings.stream();
//        Stream<String> evensLengthStringsStream = stringsStream.filter(string -> string.length() % 2 == 0);
//        List<String> evensLengthStringsList = evensLengthStringsStream.collect(Collectors.toList());
//        System.out.println(evensLengthStringsList);

//        List<String> evensLengthStringList = strings
//                .stream()
//                .filter(string -> string.length() % 2 == 0)
//                .filter(string -> Character.isDigit(string.charAt(0)))
//                .collect(Collectors.toList());
//
//        System.out.println(evensLengthStringList);

        List<Integer> evensLengthStringLenghtsList = strings
                .stream()
                .filter(string -> string.length() % 2 == 0)
                .filter(string -> Character.isDigit(string.charAt(0)))
                .map(string -> string.length())
                .collect(Collectors.toList());

        System.out.println(evensLengthStringLenghtsList);

//        System.out.println("_______");
//        System.out.println(evensLengthStringLenghtsList);
//        System.out.println("_______");
//        List<Integer> sumOfChars = strings
//                .stream()
//                .map(string -> {
//                    int sum = 0;
//                    char charArray[] = string.toCharArray();
//                    for (int i = 0; i < charArray.length; i++) {
//                        sum += charArray[i];
//                    }
//                    return sum;
//                }).collect(Collectors.toList());
//        System.out.println(sumOfChars);
//        System.out.println("_____");
//        strings.stream()
//                .forEach(string -> System.out.println("hello " + string));

        // STREAM

        // filter(Predicate<T> predicate) - фильтр, выбирает
        // из исходного стрима только те элементы, которые удовлетворяют
        // условию предиката
        // map(Function<T,R> function) - преобразует элементы
        // исходного стрима типа T в элементы типа R
        // forEach(Consumer<T> consumer) - применяет операцию из Consumer
        // последовательно к каждому элементу стрима
        // collect(Collector<T> conllector) - преобразует стрим в нужную вам коллекцию
    }
}
