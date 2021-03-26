package Labs.OO48;

import Interfaces.Dictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.Objects;

/**
 * This is an implementation of a hash table using separate chaining. Entries in
 * the array contain Nodes, which form a chain of all key-value pairs with the
 * same key hash index.
 *
 * There's no mechanism to resize the array here. There's also no real need, as
 * a table entry will contain a linked chain which may have multiple nodes.
 *
 * Some things to think about: - How would you implement the iterators for this
 * collection? - What is the runtime complexity of the methods?
 *
 * @author Stephen J. Sarma-Weierman
 * @author Damon L. Montague Jr.
 */
public class HashDictionary<K, V> implements Dictionary<K, V>
{

    private Object[] entries; // array of Nodes
    private int size;
    private static final int DEFAULT_CAPACITY = 17;

    public HashDictionary() {
        this(DEFAULT_CAPACITY);
    }

    // TODO
    public HashDictionary(int initialCapacity) {
        entries = new Object[initialCapacity];
        size = 0;
    }

    @Override
    public Iterator<K> keys() 
    {
        return new Iterator<K>() 
        {
            int count = 0;
            int countForCols = 0;
            int iterations = 0;

            @Override
            public boolean hasNext() 
            {
                return iterations < size;
            }

            public K next() 
            {
                if (!hasNext()) 
                {
                    throw new NoSuchElementException("End of collection");
                }

                Node t = (Node)entries[count];
                while (t == null) {
                    t = (Node) entries[++count];
                }
                
                for (int i = 0; i < countForCols; i++)
                {
                    /**
                     * Go up until the one we were last on
                     */
                    t = t.next;
                }

                K keyToRturn = t.getKey();

                if (t.next == null)
                {
                    count++;
                    countForCols = 0;
                }
                else
                {
                    countForCols++;
                
                }
                iterations++;
                return keyToRturn;

            }
          
        };
    }

    @Override
    public Iterator<V> elements() 
    {
        return new Iterator<V>() 
        {
            int count = 0;
            int countForCols = 0;
            int iterations = 0;

            @Override
            public boolean hasNext() 
            {
                return iterations < size;
            }

            public V next() 
            {
                if (!hasNext()) 
                {
                    throw new NoSuchElementException("End of collection");
                }

                Node t = (Node)entries[count];
                while (t == null) {
                    t = (Node) entries[++count];
                }
                
                for (int i = 0; i < countForCols; i++)
                {
                    /**
                     * Go up until the one we were last on
                     */
                    t = t.next;
                }

                V valueToReturn = t.value;

                if (t.next == null)
                {
                    count++;
                    countForCols = 0;
                }
                else
                {
                    countForCols++;
                
                }
                iterations++;
                return valueToReturn;

            }
        };
    }

    // TODO
    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) {
        int index = getHashIndex(key);
        Node n = (Node) entries[index];
        while (n != null && !n.getKey().equals(key)) {
            n = n.getNext();
        }
        if (n == null) {
            return null;
        }
        return n.getValue();
    }

    /*
     * 
     * @returns: The previous value associated with key, or null if there was no
     * mapping for key. (A null return can also indicate that the map previously
     * associated null with key.)
     * 
     */
    @Override
    public V remove(K key) {
        V value = null;
        if (size == 0 || get(key) == null || key == null) {
            return value;
        }

        int index = getHashIndex(key);
        Node rNode = (Node) entries[index];

        /**
         * 
         * 
         * 
         * 23 34 45 67 66 53 23 10 19 123
         */
        if (rNode.next == null) 
        {
            value = rNode.value;
            entries[index] = null;
            size--;
            return value;
        } 
        else if (rNode.key.equals(key) && rNode.next != null) 
        {
            /**
             * Node you want to remove is at the bottom
             */
            value = rNode.value;
            entries[index] = rNode.next;
            size--;
            return value;
        } 
        else 
        {
            Node prev = rNode;
            Node next = null;
            while (rNode != null && !rNode.key.equals(key)) {
                next = rNode.next;
                prev = rNode;
                rNode = rNode.next;
            }

            value = rNode.value;

            if (next == null) 
            {
                prev.next = null;
                size--;
                return value;
            } 
            else 
            {
                prev.next = next;
                size--;
                return value;
            }
        }
    }

    // TODO
    @Override
    @SuppressWarnings("unchecked")

    public V put(K key, V value) 
    {

        if (key == null) 
        {
            return null;
        }

        int index = getHashIndex(key);
        Node test = (Node) entries[index];
        V val = null;

        if (test == null) 
        {
            /**
             * Nothing is here
             */
            entries[index] = new Node(key, value);
            size++;
            return value;
        } 
        else 
        {
            Node prev = test;
            while (test != null && !test.key.equals(key)) 
            {
                prev = test;
                test = test.next;
            }

            if (test != null) 
            {
                val = test.value;
                test.value = value;
                return val;
            } 
            else 
            {
                test = new Node(key, value);
                prev.next = test;
                size++;
                return value;
            }
        }

    }


    /**
     * This returns an index based on the hashCode for the key object. The index
     * must be in the bounds of the array.
     *
     * @param key
     * @return
     */
    private int getHashIndex(K key) 
    {
        int capacity = entries.length;
        int index = key.hashCode() % capacity;
        if (index < 0) {
            index += capacity;
        }
        return index;
    }

    @Override
    public boolean isEmpty() 
    {
        return size == 0;
    }

    @Override
    public int size() 
    {
        return size;
    }

    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i < (int) Math.sqrt(n) + 1; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    private int nextPrime(int n) {
        int p = n + 1;
        while (!isPrime(p)) {
            p++;
        }
        return p;
    }

    private class Node {

        private K key;
        private V value;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }
}
