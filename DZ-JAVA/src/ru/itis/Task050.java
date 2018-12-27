package ru.itis;

import ru.itis.rational_fraction.RationalFraction;

/**
 * @author Aleksandrov Anrey
 *         11-703
 *         050
 */
public class Task050 {
    public static void main(String[] args) {
        RationalFraction rf = new RationalFraction(5, 10);
        rf.reduce();
        System.out.println(rf.toString());
    }
}
