package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;

    private int modCount;

    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> newElement = new Node<>(value, null);
        if (head == null) {
            head = newElement;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newElement;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = head;
        int indexElement = 0;
        while (indexElement < index) {
            current = current.next;
            indexElement++;
        }
        return current.item;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            Node<E> current = new Node<>(null, head);
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                current = current.next;
                return current.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
