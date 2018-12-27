package ru.itis;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class DictionaryTest {
    private Dictionary dictionary;

    @Before
    public void setUp(){
        try {
            dictionary = new Dictionary("Glossary.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOnNumLen1() {
        assertEquals(11, dictionary.numLen1());
    }

    @Test
    public void testOnUnique() {
        assertEquals("[smartphone, dad, buy, was, pay, i, plate, mom, tablet, that, cake, and, backpack, today, eat, word, park]",
                dictionary.unique().toString());
    }

    @Test
    public void testOnTranslate(){
        assertEquals("сегодня я купить a торт и есть тот ", dictionary.translate("today i buy a cake and eat that"));
    }

    @Test
    public void testOnInsertAndDelete(){
        String word = "abc";
        String wortTranslate = "абв";
        dictionary.insert(word, wortTranslate);
        assertTrue(word.equals(dictionary.getLast().word) && wortTranslate.equals(dictionary.getLast().translatedWord));
        dictionary.delete(word);
        assertFalse(word.equals(dictionary.getLast().word));
    }
}
