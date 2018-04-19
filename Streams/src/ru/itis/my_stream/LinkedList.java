package ru.itis.my_stream;


import java.util.Iterator;

public class LinkedList<T extends Comparable<T>> implements List<T> {

    public LinkedList(T[] array) {
        if(array.length == 0){
            head = null;
            return;
        }
        head = new Node(array[0]);
        tail = head;
        for(int i = 1; i < array.length; i++){
            tail.next = new Node(array[i]);
            tail = tail.next;
        }
        count = array.length;
    }

    public void replace(T oldValue, T newValue) {
        Node t = head;
        while(t != null){
            if(t.value.compareTo(oldValue) == 0){
                t.value = newValue;
                return;
            }
            t = t.next;
        }
    }

    public class LinkedListIterator implements Iterator<T> {

        private Node currentIndex;

        public LinkedListIterator(){
            this.currentIndex = head;
        }

        @Override
        public boolean hasNext() {
            return currentIndex != null;
        }

        @Override
        public T next() {
            Node temp = currentIndex;
            currentIndex = currentIndex.next;
            return temp.value;
        }
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
        }
    }

    public static<E extends Comparable<E>> LinkedList merge(LinkedList<E> a, LinkedList<E> b) {
        LinkedList<E> result = new LinkedList<>();

        Iterator<E> iteratorA = a.iterator();
        Iterator<E> iteratorB = b.iterator();

        E tempA = null;
        E tempB = null;

        if(iteratorA.hasNext()){
            tempA = iteratorA.next();
        }
        if(iteratorA.hasNext()) {
            tempB = iteratorB.next();
        }
        while(tempA != null && tempB != null){
            if(tempA.compareTo(tempB) == -1){
                result.add(tempA);
                if (iteratorA.hasNext())
                    tempA = iteratorA.next();
                else
                    tempA = null;
            }else {
                result.add(tempB);
                if(iteratorB.hasNext())
                    tempB = iteratorB.next();
                else
                    tempB = null;
            }
        }

        Iterator<E> iterator;
        E temp;
        if(tempA == null){
            iterator = iteratorB;
            temp = tempB;
        }else {
            iterator = iteratorA;
            temp = tempA;
        }
        while(temp != null){
            result.add(temp);
            if(iterator.hasNext())
                temp = iterator.next();
            else
                temp = null;
        }
        return result;
    }

    private Node head;
    private Node tail;

    private int count;

    public LinkedList() {
        this.count = 0;
    }

    @Override
    public int indexOf(T element) {
        return 0;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public void addToBegin(T element) {
        Node temp = head;
        head = new Node(element);
        head.next = temp;
        count++;
    }

    @Override
    public void add(T element) {
//        Node newNode = new Node(element);
//
//        if (head == null) {
//            head = newNode;
//        } else {
//            // создаем переменную, которая указывает
//            //  на начало списка
//            Node current = head;
//            // пока следующий после текущего есть
//            while (current.next != null) {
//                // идем дальше
//                current = current.next;
//            }
//            current.next = newNode;
//        }
        Node newNode = new Node(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        count++;
    }


    @Override
    public void remove(T element) {
        Node previous = head;
        Node temp;
//        if (head.value.equals(element)) {
//            head = head.next;
//            count--;
//            return;
//        }
//        while (temp != null) {
//            if (temp.value.equals(element)) {
////                previous.next = temp.next;
//                if(temp.next == null){
//                    if(temp.value.equals(head.value))
//                        head = null;
//                    else
//                        previous.next = null;
//                }else {
//                    temp.value = temp.next.value;
//                    temp.next = temp.next.next;
//                }
//                count--;
//            }else {
//                previous = temp;
//                temp = temp.next;
//            }
//        }
        while(true){
            if(head.value.equals(element)){
                head = head.next;
            }else {
                break;
            }
        }
        temp = head;
        while(temp != null){
            if(temp.value.equals(element)){
                previous.next = temp.next;
                count--;
            }else {
                previous = temp;
            }
            temp = temp.next;
        }
    }

    @Override
    public void reverse() {
        if(head == null) return;
//        Node temp1 = null;
//        Node temp2 = head.next;
//        for (int i = 0; i < count - 1; i++) {
//            temp1 = head;
//            head = temp2;
//            temp2 = head.next;
//            head.next = temp1;
//        }
//        head.next = temp1;

//        Node temp1 = head.next;
//        Node temp2 = null;
//        for (int i = 0; i < count - 1; i++) {
//            temp2 = temp1.next;
//            temp1.next = head;
//            head.next = null;
//            head = temp1;
//            temp1 = temp2;
//        }
        Node t = head.next;//запоминаем второй элемент
        head.next = null;  //и удаляем его из основной цепи для предотвращения цикла
        Node t2;
        while(t != null) {
            t2 = t.next;   //Переставляем следующий элемент в начало
            t.next = head; //
            head = t;      //
            t = t2;        //
        }
    }

    @Override
    public boolean contains(T element) {
        Node temp = head;
        while (temp != null) {
            if (temp.value.equals(element)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public Stream<T> stream(){
        return new Stream<T>(this);
    }

    public T[] toArray(){
        T[] array = (T[]) new Object[count];

        Node temp = head;
        for(int i = 0; i < count; i++){
            array[i] = temp.value;
            temp = temp.next;
        }
        return array;
    }

    @Override
    public int size() {
        return count;
    }

    public String toString(){
        String str = "[  ";
        Node temp = head;
        while (temp != null){
            str += temp.value + "  ";
            temp = temp.next;
        }
        str += "]";
        return str;
    }
}
