package ru.itis.complex_number;

/**
 * @author Aleksandorv Andrey
 *         11-703
 *         for 051
 */

public class ComplexNumber {
    private double a;
    private double b;

    public ComplexNumber(){
        a = b = 0;
    }

    public ComplexNumber(double a, double b){
        this.a = a;
        this.b = b;
    }

    public ComplexNumber add(ComplexNumber cn) {
        return new ComplexNumber(a + cn.a, b + cn.b);
    }

    public void add2(ComplexNumber cn){
        a += cn.a;
        b += cn.b;
    }

    public ComplexNumber sub(ComplexNumber cn) {
        return new ComplexNumber(a - cn.a, b - cn.b);
    }

    public void sub2(ComplexNumber cn){
        a -= cn.a;
        b -= cn.b;
    }

    public void multNumber(double t){
        a *= t;
    }

    public ComplexNumber mult(ComplexNumber cn){
        return new ComplexNumber(a * cn.a - b * cn.b, b * cn.a + a * cn.b);
    }

    public void mult2(ComplexNumber cn){
        a = a * cn.a - b * cn.b;
        b = b * cn.a + a * cn.b;
    }

    public ComplexNumber div(ComplexNumber cn) {
        return new ComplexNumber((a * cn.a + b * cn.b)/(cn.a * cn.a + cn.b * cn.b),
                (b * cn.a - a * cn.b)/(cn.a * cn.a + cn.b * cn.b));
    }

    public void div2(ComplexNumber cn) {
        a = (a * cn.a + b * cn.b)/(cn.a * cn.a + cn.b * cn.b);
        b = (b * cn.a - a * cn.b)/(cn.a * cn.a + cn.b * cn.b);
    }

    public double length(){
        return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
    }

    public String toString() {
        String str = b + " * i ";
        if (a > 0){
            str += "+ ";
        }
        return str + a;
    }

    public double arg(){
        return Math.atan(b/a);
    }

    public ComplexNumber pow(double t){
        double r = Math.pow(length(), t);
        double arg = arg();
        return new ComplexNumber(r*Math.cos(t * arg), r*Math.sin(t * arg));
    }

    public boolean equals(ComplexNumber cn){
        return (a == cn.a && b == cn.b);
    }

}