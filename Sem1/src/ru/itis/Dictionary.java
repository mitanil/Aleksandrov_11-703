package ru.itis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class Dictionary implements IDictionary, Iterable{

    private class NodeIterator implements Iterator{

        NodeIterator(){

        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Node next() {
            return null;
        }
    }

    @Override
    public Iterator iterator() {

        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    class Node{
        String word;
        String translatedWord;
        Node next;

        Node(String k, String j) {
            word = k;
            translatedWord = j;
            next = null;
        }
    }

    private Node head = null;
    private Node last = null;
    private int length = 0;

    public Node getHead(){
        return head;
    }

    public Node getLast(){
        return last;
    }

    Dictionary(String filename) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(filename));
        Scanner reader = new Scanner(new FileReader(filename));
        String[] words;
//        if((str = reader.readLine()) != null){
        if(reader.hasNext()){
            words = reader.nextLine().split(" - ");
            head = new Node(words[0], words[1]);
            last = head;
            length++;
        }else {
            throw new NullPointerException();
        }
        while (reader.hasNext()) {
            words = reader.nextLine().split(" - ");
            last.next = new Node(words[0], words[1]);
            last = last.next;
            length++;
        }
    }

    @Override
    public void show() {
        Node temp = head;
        while (temp != null){
            System.out.println(temp.word + " - " + temp.translatedWord);
            temp = temp.next;
        }
    }

    @Override
    public void insert(String k, String v) {
        last.next = new Node(k, v);
        last = last.next;
        length++;
    }

    @Override
    public void delete(String k) {
        Node previous = head;
        Node temp = head;
        while(temp != null){
            if(temp.word.equals(k)){
                length--;
                if(temp.equals(head)){
                    head = temp.next;
                    previous = head;
                    temp = head;
                }else if(temp.equals(last)){
                    previous.next = null;
                    last = previous;
                    return;
                }else{
                    previous.next = temp.next;
                    temp = temp.next;
                }
            }else {
                previous = temp;
                temp = temp.next;
            }
        }
    }

    @Override
    public Set<String> unique() {
        Set<String> uniqueGlossary = new HashSet<>();
        Node temp = head;
        while(temp != null){
            uniqueGlossary.add(temp.word);
            temp = temp.next;
        }
        return uniqueGlossary;
    }

    @Override
    public int numLen1() {
        int count = 0;
        Node temp = head;
        while (temp != null){
            if(Math.abs(temp.word.length() - temp.translatedWord.length()) < 2)
                count ++;
            temp = temp.next;
        }
        return count;
    }

    @Override
    public String translate(String text) {
        String[] words = text.split(" ");
        Node temp = head;
        while (temp != null){
            for(int i = 0; i < words.length; i++){
                if(temp.word.equals(words[i])){
                    words[i] = temp.translatedWord;
                }
            }
            temp = temp.next;
        }
        String result = "";
        for(String str : words){
            result += str + " ";
        }
        return result;
    }

}
