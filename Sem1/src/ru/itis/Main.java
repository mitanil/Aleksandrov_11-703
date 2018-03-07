package ru.itis;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        IDictionary dictionary;
        try {
            dictionary = new Dictionary("Glossary.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
            return;
        } catch (NullPointerException e) {
            System.err.println("Файл не содержит слов!");
            return;
        } catch (IOException e) {
            System.err.println("Ошибка при создании словаря!");
            return;
        }

//        dictionary.show();
//        System.out.println(dictionary.numLen1());
//        dictionary.delete("word");
//        System.out.println("");
//        dictionary.show();
        System.out.println(dictionary.unique());
//        System.out.println(dictionary.translate("today i buy a cake and eat that"));


    }
}
