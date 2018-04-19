package ru.itis;

import java.util.function.Function;

/**
 * 05.04.2018
 * StringLengthFunction
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class StringLengthFunction implements Function<String, Integer> {
    @Override
    public Integer apply(String string) {
        return string.length();
    }
}
