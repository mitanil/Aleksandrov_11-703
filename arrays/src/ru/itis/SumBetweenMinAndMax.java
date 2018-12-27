package ru.itis;
public class SumBetweenMinAndMax {
    public static void main(String[] args) {
        int mass[] = WorkWhitMass.createMass();
        int min = 0, max = 0, sum = 0;
        for (int i = 1; i < mass.length; i++) {
            if (mass[min] > mass[i]) {
                min = i;
            }
            if (mass[max] < mass[i]) {
                max = i;
            }
        }
        if(min > max){
            int t = min;
            min = max;
            max = t;
        }
        for (int i = min + 1; i < max; i++) {
            sum += mass[i];
        }
        System.out.println("Минимальное число: " + mass[min]);
        System.out.println("Максимальное число: " + mass[max]);
        System.out.println("Сумма чисел между ними: " + sum);
    }
}
