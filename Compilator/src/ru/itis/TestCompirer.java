package ru.itis;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestCompirer {
    Compirer compirer;

    @Before
    public void begin(){
        compirer = new Compirer();
    }

    @Test
    public void test1(){
        compirer.analyze("A1:=2;A2:=3;A3:=A1-A2;");
        ArrayList<Variable> correctResult = new ArrayList<>();
        correctResult.add(new Variable("A1", 2));
        correctResult.add(new Variable("A2", 3));
        correctResult.add(new Variable("A3", -1));
        assertTrue(correctResult.equals(compirer.getVariables()));
    }

    @Test
    public void test2(){
        compirer.analyze("A1:=5;A2:=1;A3:=23-A2+A1;A1:=2*A3+A2;");
        ArrayList<Variable> correctResult = new ArrayList<>();
        correctResult.add(new Variable("A1", 55));
        correctResult.add(new Variable("A2", 1));
        correctResult.add(new Variable("A3", 27));
        assertTrue(correctResult.equals(compirer.getVariables()));
    }

    @Test(expected = SyntaxException.class)
    public void testSyntaxError(){
        compirer.analyze("A1:=5;A2:=1;A3:=23-A2+A1A1:=2*A3+A2;");
    }

    @Test(expected = InitializeException.class)
    public void testInitializeError(){
        compirer.analyze("A1:=5;A2:=1;A3:=23-A2+A5;A1:=2*A3+A2;");
    }
}
