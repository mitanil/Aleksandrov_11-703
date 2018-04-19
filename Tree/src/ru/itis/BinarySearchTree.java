package ru.itis;

public interface BinarySearchTree<T extends Comparable> {
    void insert(T element);
    boolean remove(T element);
    boolean contains(T element);
    void print();
    void printByLevels();
}