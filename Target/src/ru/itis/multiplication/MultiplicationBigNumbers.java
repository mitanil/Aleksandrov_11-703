package ru.itis.multiplication;

public class MultiplicationBigNumbers {
    static BigNumber firstNumber = new BigNumber("813872857687262185678247");
    static BigNumber secondNumber = new BigNumber("3647328247658749126528");

    public static void main(String[] args) {
        System.out.println(firstNumber.multiplication(secondNumber));
    }

}
