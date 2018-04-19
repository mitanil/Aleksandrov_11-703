package ru.itis;


public class Main {

    public static void main(String[] args) {
        Compirer compirer = new Compirer();
        compirer.analyze("A1:=2;A2:=3;A3:=A1-A2;");
    }
}
