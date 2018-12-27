package ru.itis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        int countOfNewBocks = 0;
        Scanner s = null;
        try {
            s = new Scanner(new FileReader(new File("Data.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        Считываем количество вершин и ветвей
        int countOfRoots = 0, maxBranches = 0;
        {
            int[] mass = toIntArray(s.nextLine().split(" "));
            countOfRoots = mass[0];
            maxBranches = mass[1];
        }

//        Создаем массив, где элемент соответсвует номеру вершины, а значение количеству потомков
        int countOfBranches[] = new int[countOfRoots];
        while(s.hasNext()){
            countOfBranches[Integer.parseInt(s.nextLine())] ++;
        }

//        Проходимся по вершинам
//        Если количество потомков больше допустимого, то смотрим сколько блоков создает конкретно эта вершина
        for(int i = 0; i < countOfRoots; i++){
            if(countOfBranches[i] > maxBranches){
                countOfNewBocks += getCountOfNewBlocks(countOfBranches[i], maxBranches);
            }
        }
        System.out.println(countOfNewBocks);
    }

    private static int getCountOfNewBlocks(int countOfBranch, int maxBranches) {
        int countOfNewBlocks = 0;
        int t = maxBranches;
//        Создаем максималько допустимое количество блоков на уровне.
//        Если на следующем уровне не сможем разместить имеющееся количество вершин,
//        переходим на следующий уровень и повторяем действия
        while (t * maxBranches < countOfBranch) {
            countOfNewBlocks += t;
            t *= maxBranches;
        }

//        Если количество допустимых вершин равно количеству имеющихся, записываем их и уходим,
//        иначе убираем по одному блоку, пока при следующем исключении блока вершины не будут влезать
        if (t * maxBranches == countOfBranch) {
            countOfNewBlocks += t;
        } else {
            int k = t * maxBranches;
            while (k - countOfBranch >= maxBranches - 1) {
                t--;
                k -= maxBranches;
                countOfBranch--;
            }
            countOfNewBlocks += t;
        }
        return countOfNewBlocks;
    }

    private static int[] toIntArray(String[] str) {
        int[] array = new int [str.length];
        for(int i = 0; i< array.length; i++){
            array[i] = Integer.parseInt(str[i]);
        }
        return array;
    }


}
