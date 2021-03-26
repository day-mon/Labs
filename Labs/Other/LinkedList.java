package Labs.Other;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;


    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;

    }

    public LinkedList(T val) {
        this.head = new Node(val);
        this.tail = null;
        this.size = 0;
    }


    public void insertFront(T val) {
        Node newHead = new Node(val);
        newHead.setNext(head);
        head = newHead;
        size++;
    }

    public void insertBack(T val) {
        Node newNode = new Node(val);
        tail.setNext(newNode);
        tail = newNode;
        size++;

    }

    public void remove(T val) {
        Node current = head;
        Node prev = head;

        if (this.head.getValue().equals(val)) {
            this.head = head.getNext();
            size--;
        }

        while (current != null) {
            if (current.getValue().equals(val)) {
                prev.setNext(current.getNext());
                size--;
                break;
            }

            prev = current;
            current = current.getNext();
        }
    }


    public T find(int index) {
        Node current = head;
        int count = 0;

        if (index > size) return null;

        while (current != null) {
            if (count == index){
                return (T) current.getValue();
            }
            count++;
            current = current.getNext();
        }

        return null;
    }


    public boolean search(T val) {
        Node current = head;

        while (current != null) {
            if (current.getValue().equals(val)){
                return true;
            }
            current = current.getNext();
        }
        return true;
    }


    
    
}
