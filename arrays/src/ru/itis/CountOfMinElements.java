package ru.itis;
public class CountOfMinElements {
    public static void main(String[] args) {
        int min, count = 1;
        int mass[] = WorkWhitMass.createMass();
        min = mass[0];
        for (int i = 1; i < mass.length; i++) {
            if(min > mass[i]){
                min = mass[i];
                count = 1;
            }else
                if(min == mass[i])
                    count ++;
        }
        System.out.println("Минимальные число:" + min);
        System.out.println("Количество минимальных чисел:" + count);
    }
}
