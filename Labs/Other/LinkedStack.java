package Labs.Other;

import java.util.EmptyStackException;

public class LinkedStack<T> {
    Node<T> head;
    int size;



    /**
     * Stacks are 
     * L - LAST
     * I - IN
     * F - FIRST
     * O - OUT
     * 
     * 
     * With a LinkedStack that uses the head best time complexity you can achieve will be O(1)
     * With a LinkedStack that uses the tail best time complexity you can achieve will be O(n)
     *      - This is is because with the tail you would have to traverse back to the start
     *         of the LinkedList to pop off the last value you added, this way wouldnt be very effecient
     *         for most cases.
     */

    public LinkedStack() {
        this.head = null;
        this.size = 0;
    }

    public LinkedStack(T val) {
        this.head = new Node(val);
        this.size = 0;
    }

   /*
        Time complexity: O(1)
   */
    public void push(T val) {
        Node newNode = new Node(val);
        newNode.setNext(this.head);
        head = newNode;
        size++;
    }

   /*
        Time complexity: O(1)
   */
    public T pop() {
        if (this.head == null) {
            throw new EmptyStackException();
        } else {
            T val = head.getValue();
            head.setNext(head.getNext());
            size--;
            return val;
        }
    }

   /*
        Time complexity: O(1)
   */
    public T peek() {
        return head.getValue();
    }

   /*
        Time complexity: O(1)
   */
    public boolean isEmpty() {
        return head == null;
    }


    
}
