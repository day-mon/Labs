package Labs;

import java.util.Arrays;
import java.util.LinkedList;
import Interfaces.Set;

public class LinkedSet<T> implements Set<T> {

    /**
     *      **IGNORE** **IGNORE** **IGNORE**
     *                  NOTES
     *   How linked chains work:
     *   Unlike Arrays a LinkedChain (usually) is not positioned side by side in memory.
     *   So in order to reference them we use Nodes, each node will store the value, and along with that value
     *   will be a reference in memory to the next value. Each node will follow this formula, and youll know when you hit the
     *   last node because the next reference/value will be null.
     *
     *   Way to cycle through all elements:
     *      while (nodeVar != null) {
     *          sysout(nodeVar.getValue()) <- Gets value of current node
     *          nodeVar = nodeVar.getNext()); <- Advances to the next node if its not null
     *       }
     *
     *   How garbage collector works:
     *   The garbage collector is deletes any node that isnt being refrences by something else
     *   Ex:
     *   head[1] -> 2 -> 3 -> 4 -> 5 -> null
     *
     *   if we want to delete two we need to set the next node to the node after the one we want to delete 2,
     *   You would need link 1 to 3, and because 2 has no reference to anything the garbage collector would delete it.
     *
     *   How to determine equality:
     *   current.getValue().equals(obj).
     *

     * Linked chain implementation of Set interface
     * @author Damon L Montague Jr.
     * @param <T>
     */


    private int size;
    private Node head;

    /**
     * If head is null that means nothing is in the collection therefore,
     * we will set the argument we are passing as the head.
     *
     * Otherwise cycle through ever single element until the next value is null,
     * indicating you are at the end of the collection, and when you are,
     * set the next value to the argument you are passing through, increase size, @return true.
     *
     * @param obj the object being added.
     * @return boolean
     */
    @Override
    public boolean add(T obj) {

        if (contains(obj) || obj == null) {
            return false;
        } else {
         Node n = new Node(obj);
         n.setNext(head);
         head=n;
         size++;
            return true;
    }


    }

    /**
     * Check to see if object is the head, if it is set the next item in the collection to the head decrease size, return true.
     * Else go through all the items in the collection, check if the next object is null and then check if the next item is in the collection,
     * If it is link the current node we are on to the node 2 objects away (this will derefernce the item we want and it will be deleted by the garbage collector,
     * decrease size, return true.
     *
     * @param obj the object being removed.
     * @return boolean
     *
     * Time complexity: O(n)
     */
    @Override
    public boolean remove(T obj) {
        Node current = head;

        if (head.getValue().equals(obj)) {
            head = head.getNext();
            size--;
            return true;
        } else {
            while (current != null) {

                if ((current.getNext() != null) && (current.getNext().getValue().equals(obj))) {
                    current.setNext(current.getNext().getNext());
                    size--;
                    return true;
                }
                current = current.getNext();
            }
        }
        return false;
    }


    /**
     * Define T so it can be updated or return null.
     * Iterate through every element in the collection until the 2nd to the last element is null,
     * When you find the null value two away from the current value that means the next value is the last non-null value because the last value in the collection is null.
     * Set val to the next value, set it to null, decrease size.
     * @return item being removed
     * Time complexity: O(n)
     */
    @Override
    public T remove() {
        T rem =  null;
        if (isEmpty())  {
          return null;
        } else  {
         rem = head.getValue();
         head = head.getNext();
         size--;
         return rem;
        }       
       
       /** Alternative  approach
        *  while (current != null) {
            if(current.getNext().getNext() == null) {
                val = current.getNext().getValue();
                current.setNext(null);
                size--;
            }
            current = current.getNext();
        }

        return val;
        **/
    }


    /**
     *
     * @param obj the object to be checked if it is contained in the set.
     * @return
     *
     * Step 1: While the current node isnt the last node
     * Step 2: Get the value of the node we are on to check to see if it is equal.
     * Step 3: If it finds it **return true**
     * Step 4: Else, set current to the next node in the chain.
     *
     * If it is never found return false;
     *
     * O(n) Time complexity.
     */
    @Override
    public boolean contains(T obj) {
        Node current = this.head;

        while( current != null ) {
            if(current.getValue().equals(obj)) {
                return true;
            } else {
                current = current.getNext();
            }
        }

        return false; // return false if nothing is found.


    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * If you set the head to null that means it will have to be last reference in the collection
     * The garbage collector will delete anything after the head node because it will have
     * no refrence to anything but things that have been dereference be setting the head to null.
     */
    @Override
    public void clear() {
        this.head = null;
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * @TODO: fixed 09/06/2020
     * Define the array.
     * First check if the size <= 0 if it is return an empty array
     * else define the array with the size of size-1, and iterate through the values.
     * @return an array of values.
     *
     * Seems like O(n)
     */
    @Override
    public Object[] toArray() {
        Node current = head;
        Object [] arr;

        if (size <= 0 ) {
            return arr = new Object[0];
        } else {
            arr = new Object[size-1];
            for (int i = 0; i < size - 1; i++, current = current.getNext()) {
                arr[i] = current.getValue();

            }
        }

        return arr;

    }

    /**
     * Question(s):
     *  What if there is multiple of the same type of object in the set,
     *  how would we determine which node we want the previous value of.
     * @param obj
     * @return
     */
    private Node getNodeBefore(T obj) {
        Node current = head;
        Node value = new Node(obj);

        while (current != null) {
            if (current.getNext() != null && current.getNext().getValue().equals(obj))
            {
                return current;
            }

            current = current.getNext();
        }
     return null;
    }




    private class Node {
        private T value;
        private Node next;

        public Node(T value) {
            this.value = value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return this.next;
        }
    }
}
