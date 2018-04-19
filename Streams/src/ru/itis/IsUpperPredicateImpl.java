package ru.itis;

import java.util.function.Predicate;

/**
 * 05.04.2018
 * IsUpperPredicateImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class IsUpperPredicateImpl implements Predicate<String> {
    @Override
    public boolean test(String string) {
        Character firstCharacter = string.charAt(0);
        return Character.isUpperCase(firstCharacter);
    }
}
