package ru.itis;

import java.util.Set;

public interface IDictionary {
    void show();
    void insert(String k, String v);
    void delete(String k);
    Set<String> unique();
    int numLen1();
    String translate(String text);
}
