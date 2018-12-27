package ru.itis;

/**
 * 15.03.2018
 * HashMapImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class HashMapImpl<K, V> implements Map<K, V> {

    class Node<Key, Value> {
        Key key;
        Value value;
        Node next;
        Node last;

        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
        }

    }

    private Node<K,V> map[];

    public HashMapImpl() {
//        map = (Node[])new Object[10];
        map = new Node[10];
    }

    @Override
    public void put(K key, V value) {
        int position = Math.abs(key.hashCode() % 10);
        if(map[position] == null){
            map[position] = new Node(key, value);
            map[position].last = map[position];
        }else{
            Node temp = map[position];
            while (temp != null){
                if(temp.key.equals(key)){
                    temp.value = value;
                    return;
                }
                temp = temp.next;
            }
            map[position].last.next = new Node(key, value);
            map[position].last = map[position].last.next;
        }
    }

    @Override
    public V get(K key) {
        int position = Math.abs(key.hashCode() % 10);
        if(map[position] == null)
            return null;
        Node temp = map[position];
        while (temp != null){
            if(key.equals((K)temp.key)){
                return (V)temp.value;
            }
            temp = temp.next;
        }
        return null;
    }
}
