package Labs.OO48;

import Interfaces.Dictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
 * @author Damon Montague
 */
public class HashDictionary<K, V> implements Dictionary<K, V>
{

    private Object[] entries; //array of Nodes
    private int size;
    private static final int DEFAULT_CAPACITY = 17;

    public HashDictionary()
    {
        this(DEFAULT_CAPACITY);
    }

    //TODO
    public HashDictionary(int initialCapacity)
    {
        entries = new Object[initialCapacity];
        size = 0;
    }

    //TODO
    @Override
    public Iterator<K> keys()
    {
        return new Iterator<K>()
        {
            private int count = 0;
            private int currentIndex = 0;
            private Node currentNode = null;

            public boolean hasNext()
            {
                return count < size; // If count < size, implicitly there is a next Node
            }

            public K next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                }
                while (currentNode == null)
                {
                    currentNode = (Node) entries[currentIndex++];
                }
                K tempKey; // Stores the previous key in currentNode
                tempKey = currentNode.getKey();
                currentNode = currentNode.getNext();
                count++;
                return tempKey;
            }
        };
    }

    //TODO
    @Override
    public Iterator<V> elements()
    {
        return new Iterator<V>()
        {
            private int count = 0;
            private int currentIndex = 0;
            private Node currentNode = null;

            public boolean hasNext()
            {
                return count < size; // If count < size, implicitly there is a next Node
            }

            public V next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                }
                while (currentNode == null)
                {
                    currentNode = (Node) entries[currentIndex++];
                }
                V tempValue; // Stores the previous value in currentNode
                tempValue = currentNode.getValue();
                currentNode = currentNode.getNext();
                count++;
                return tempValue;
            }
        };
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
        if (index < 0)
        {
            index += capacity;
        }
        return index;
    }

    //TODO
    @SuppressWarnings("unchecked")
    @Override
    public V get(K key)
    {
        int index = getHashIndex(key);
        Node n = (Node) entries[index];
        while (n != null && !n.getKey().equals(key))
        {
            n = n.getNext();
        }
        if (n == null)
        {
            return null;
        }
        return n.getValue();
    }


    //TODO
    @Override
    public V put(K key, V value)
    {
        int hashedIndex = getHashIndex(key);
        // check if index is null
        // if null node is first index
        // increment size
        // else iterate through chain in index
        // if a node matches key
        // save the value
        // replace value
        // return value
        // else add node to end of chain
        // increment size

        Node newNode = new Node(key, value);
        if (entries[hashedIndex] == null)
        {
            entries[hashedIndex] = newNode;
            size++;
            return value;
        }
        Node traversalNode = (Node) entries[hashedIndex];
        Node prev = traversalNode;
        while (traversalNode != null)
        {
            prev = traversalNode;
            if (traversalNode.getKey().equals(key))
            {
                V returnValue = traversalNode.getValue();
                traversalNode.setValue(value);
                return returnValue;
            }
            traversalNode = traversalNode.getNext();
        }
        traversalNode = newNode;
        prev.next = traversalNode;
        size++;
        return null;

    }

    //TODO

    /**
     * If the key is in the collection of keys, return the associated
     * value and remove both key and value. Otherwise, return null and leave
     * collections unaltered.
     * Precondition: key != null
     * Postcondition: key, and associated value (if any), are not in dictionary
     *
     * @param key
     * @return value associated with key
     */
    @Override
    public V remove(K key)
    {
        // hash code
        // if key is not contained, return null
        // if it is go to the index
        // iterate through
        int hashedIndex = getHashIndex(key);
        if (entries[hashedIndex] == null)
        {
            return null;
        }
        Node currentNode = (Node) entries[hashedIndex];
        if (currentNode.getKey().equals(key))
        {
            V returnValue = currentNode.getValue();
            entries[hashedIndex] = currentNode.getNext();
            currentNode.setNext(null);
            size--;
            return returnValue;
        }
        Node previousNode = currentNode;
        currentNode = currentNode.getNext();
        while (currentNode != null)
        {
            if (currentNode.getKey().equals(key))
            {
                V returnValue = currentNode.getValue();
                previousNode.setNext(currentNode.getNext());
                currentNode.setNext(null);
                size--;
                return returnValue;
            }
            currentNode = currentNode.getNext();
            previousNode = previousNode.getNext();
        }
        return null;
    }

    //helper method that returns position of Node
    public int positionOfNode(K key)
    {
        int index = 0;
        Node currentNode = (Node) entries[index];

        // If the first Node stores the key, return index 0.
        if (((Node) entries[index]).getKey().equals(key))
        {
            return index;
        }

        // While the index is less than size, iterate through dictionary.
        // If the Node that stores the key is found, return index of Node.
        while (index < size)
        {
            if (currentNode == null)
            { // If Node == null, index of array is null or currentNode is the final Node.
                index++;
                currentNode = (Node) entries[index];
            }
            else
            { // Else, there are more Nodes in array, iterate and check.
                if (currentNode.getKey().equals(key))
                {
                    return index;
                }
                currentNode = currentNode.getNext(); // Iterates through list.
            }
        }
        // If the Node is not contained, return -1.
        return -1;
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
