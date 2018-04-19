package ru.itis;

import java.util.List;
import java.util.function.Predicate;

/**
 * 05.04.2018
 * StringsAnalyzer
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class StringsAnalyzer {
    public void analyze(List<String> strings, Predicate<String> stringPredicate) {
        for (String string : strings) {
            System.out.println(stringPredicate.test(string));
        }
    }
}
