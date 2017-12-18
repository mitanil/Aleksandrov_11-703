package ru.itis;

/**
 * Created by Admon on 18.12.2017.
 */
public class MultiplicationOfNumbersAfterMax {
    public static void main(String[] args) {
        int mass[] = WorkWhitMass.createMass();
        int max = mass[0], res = 1;
        for(int i = 1; i < mass.length; i++){
            if (max > mass[i]) {
                if(mass[i] != 0){
                    res *= mass[i];
                }
            }else if (max < mass[i]) {
                max = mass[i];
                res = 1;
            }
        }
        System.out.println("Максимальное число: " + max);
        System.out.println("Произведение: " + res);
    }
}
