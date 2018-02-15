package ru.itis.multiplication;

import java.math.BigInteger;

public class BigNumber {
    String number;
    public BigNumber(String str) {
        number = str;
    }

    private String reverse(String number) {
        String str = "";
        for(int i = number.length() - 1; i >= 0; i--){
            str += number.charAt(i);
        }
        return str;
    }

    private void sumOneSymbol(char a, char b, IntermediateResult c){
        c.getNumberFromString(String.valueOf(a + b + c.getDecade() - '0' *3));
    }

    private void multiplicationOneSymbol(char a, char b, IntermediateResult c) {
        c.getNumberFromString(String.valueOf((a - '0') * (b - '0') + c.getDecade() - '0'));
    }


    public BigNumber sum(BigNumber bigNumber){
        String str = "";
        IntermediateResult ir = new IntermediateResult();
//      Складываем цифры пока не кончится наименьшее число.
        int min = Math.min(number.length(), bigNumber.toString().length());
        for(int i = 1; i <= min; i++){
            sumOneSymbol(
                    number.charAt(number.length() - i),
                    bigNumber.toString().charAt(bigNumber.toString().length() - i),
                    ir);
            str += ir.getUnit();
        }

//      Закидываем в число все, что осталось от наибольшего.
        String temp;
        if(number.length() == min){
            temp = bigNumber.toString();
        }else
            temp = number;

        for(int i = temp.length() - min - 1; i >= 0; i--){
            sumOneSymbol(
                    temp.charAt(i),
                    '0',
                    ir);
            str += ir.getUnit();
        }
        if(ir.getDecade() != '0'){
            str += ir.getDecade();
        }
        str = reverse(str);
        return new BigNumber(str);
    }

    public BigNumber multiplication(BigNumber bigNumber){
        BigNumber newBigNumber = new BigNumber("0");
        String n = "";
        for(int i = bigNumber.toString().length() - 1; i >= 0; i --){
            String temp = "";
            IntermediateResult ir = new IntermediateResult();
            for(int j = number.length() - 1; j >= 0; j--){
                char d = ir.getDecade();
                multiplicationOneSymbol(number.charAt(j),
                        bigNumber.toString().charAt(i),
                        ir);
                temp += ir.getUnit();
            }
            if(ir.getDecade() != '0'){
                temp += ir.getDecade();
            }
            temp = reverse(temp);
            temp += n;
            n += '0';
            newBigNumber = newBigNumber.sum(new BigNumber(temp));
        }
        return newBigNumber;
    }

    public String toString(){
        return number;
    }




}
