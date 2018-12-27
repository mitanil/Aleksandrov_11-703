package ru.itis.my_stream;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class Stream<T extends Comparable<T>> {

    private LinkedList<T> list;

    public Stream(LinkedList<T> ts) {
        list = ts;
    }

    public Stream<T> map(Function<T, T> c) {
        LinkedList<T> newList = new LinkedList<>();
        for(T t: list){
            newList.add(c.apply(t));
        }
        return new Stream<T>(newList);
    }

    public Stream sorted(){
        T array[] = list.toArray();
        Arrays.sort(array);
        return new Stream<T>(new LinkedList<T>(array));
    }

    public Stream filter(Predicate<T> c){
        LinkedList<T> linkedList = new LinkedList<T>();
        for (T t : list)
            if(c.test(t)) linkedList.add(t);
        return new Stream<T>(linkedList);
    }

    public LinkedList<T> toList(){
        return list;
    }

}
