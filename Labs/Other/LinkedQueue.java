package Labs.Other;

import java.util.NoSuchElementException;

public class LinkedQueue<E> {


    Node<E> front;
    Node<E> back;
    int size;

    public void enqueue(E element) {
        Node newNode = new Node(element);
        if (front == null && back == null) {
            front.setNext(newNode);
        } else {
            back.setNext(newNode);
        }
        back = newNode;

    }

    public E dequeue() {
        if (front == null) {
            throw new NoSuchElementException();
        } else {
            E val = front.getValue();
            front = front.getNext();
            return val;
        }
    }

    public E peak() {
        if (front == null) {
            throw new NoSuchElementException();
        } else {
            return front.getValue();
        }

    }

    public boolean isEmpty() {
        return front == null && back == null;
    }

    
}
