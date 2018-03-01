package ru.itis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Polinom3 {

    private class Node {
        int x, y, z;
        Node next;


        Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private Node head;
    private Node tail;
    int length = 1;

    public Polinom3(String filename) throws IOException {
        Scanner reader = new Scanner(
                new FileReader(filename));
        String[] polinom = reader.nextLine().split(" ");
        head = new Node(Integer.parseInt(polinom[0]), Integer.parseInt(polinom[1]), Integer.parseInt(polinom[2]));
        tail = head;
        while (reader.hasNext()){
            polinom = reader.nextLine().split(" ");
            tail.next = new Node(Integer.parseInt(polinom[0]), Integer.parseInt(polinom[1]), Integer.parseInt(polinom[2]));
            tail = tail.next;
            length++;
        }
//        Node t = new Node(0, 0, 0);
//        head = new Node(0, 0, 0);
//        t.next = head;
//        tail = t;
//        for (int k = 0; k < polinom.length; k++){
//            tail = tail.next;
//            tail.next = readPolinom(polinom[k]);
//        }
        sort();
    }

//
//    private Node readPolinom(String polinom) {
//        Node temp = new Node(0,0,0);
//        for (int i = 0; i < polinom.length(); i++) {
//            switch (polinom.charAt(i)) {
//                case 'x':
//                    i++;
//                    if (i == polinom.length() || !(polinom.charAt(i) > '0' && polinom.charAt(i) <= '9')) {
//                        temp.x = 1;
//                        i--;
//                    } else {
//                        temp.x = polinom.charAt(i) - '0';
//                    }
//                    break;
//                case 'y':
//                    i++;
//                    if (i == polinom.length() || !(polinom.charAt(i) > '0' && polinom.charAt(i) <= '9')) {
//                        temp.y = 1;
//                        i--;
//                    } else {
//                        temp.y = polinom.charAt(i) - '0';
//                    }
//                    break;
//                case 'z':
//                    i++;
//                    if (i == polinom.length() || !(polinom.charAt(i) > '0' && polinom.charAt(i) <= '9')) {
//                        temp.z = 1;
//                        i--;
//                    } else {
//                        tail.z = polinom.charAt(i) - '0';
//                    }
//                    break;
//                default:
//            }
//        }
//        return temp;
//    }

    public String toString() {
        String str = "";
        Node temp = head;
        while (temp.next != null) {
            if(temp.x != 0){
                str += "x" + temp.x;
            }
            if(temp.y != 0){
                str += "y" + temp.y;
            }
            if(temp.z != 0){
                str += "z" + temp.z;
            }
            str += " + ";
            temp = temp.next;
        }
        if(temp.x != 0){
            str += "x" + temp.x;
        }
        if(temp.y != 0){
            str += "y" + temp.y;
        }
        if(temp.z != 0){
            str += "z" + temp.z;
        }
        return str;
    }

    private void sort(){
        Node prev = new Node(0, 0, 0);
        prev.next = head;
        Node tempPrev = prev;
        Node temp;
        for(int i = length - 1; i >= 0; i--){
            tempPrev = prev;
            temp = tempPrev.next;
            for(int j = 0; j< i -1; j++){
                if(temp.x <= temp.next.x){
                    if(temp.x < temp.next.x){
                        tempPrev.next = temp.next;
                        temp.next = temp.next.next;
                        tempPrev.next.next = temp;
                    }else if(temp.y <= temp.next.y) {
                        if (temp.y < temp.next.y) {
                            tempPrev.next = temp.next;
                            temp.next = temp.next.next;
                            tempPrev.next.next = temp;
                        } else if (temp.z <= temp.next.z) {
                            if (temp.z < temp.next.z) {
                                tempPrev.next = temp.next;
                                temp.next = temp.next.next;
                                tempPrev.next.next = temp;
                            }
                        }
                    }
                }
                tempPrev = temp;
                temp = temp.next;
            }
        }
        head = prev.next;
    }

    public void insert(int coef, int deg1, int deg2, int deg3){

    }

    public void delete(int deg1, int deg2, int deg3) {

    }

    public void add(Polinom3 p){

    }

    public void derivate(int i){

    }

    public int value(int x, int y, int z){
        return 237864;
    }

    public int minCoef () {
        return 8737;
    }
}
