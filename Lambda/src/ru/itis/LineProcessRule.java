package ru.itis;

/**
 * 28.02.2018
 * LineProcessRule
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface LineProcessRule {
    // интерфейс с одним методом -> такой
    // интерфейс называется функциональным
    // для таких интерфейсов доступны лямбда выражения
    String process(String line);
}
