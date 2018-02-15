package ru.itis.multiplication;


public class IntermediateResult {
    private char unit = '0';
    private char decade = '0';

    public char getUnit() {
        return unit;
    }

    public void setUnit(char unit) {
        this.unit = unit;
    }

    public char getDecade() {
        return decade;
    }

    public void setDecade(char decade) {
        this.decade = decade;
    }

    public void getNumberFromString(String number){
        if(number.length() == 2){
            decade = number.charAt(0);
            unit = number.charAt(1);
        }else {
            decade = '0';
            unit = number.charAt(0);
        }
    }
}
