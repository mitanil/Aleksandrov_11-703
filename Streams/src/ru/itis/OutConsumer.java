package ru.itis;

import java.util.function.Consumer;

/**
 * 05.04.2018
 * OutConsumer
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class OutConsumer implements Consumer<String> {
    @Override
    public void accept(String string) {
        System.out.println(string);
    }
}
