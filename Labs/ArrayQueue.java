package Labs;

import java.util.Arrays;
import java.util.NoSuchElementException;
import Interfaces.Queue;


/**
 * Throws Expecption:
 * add(e), remove(), element()
 * Returns Value:
 * offer(e), poll(), peek()
 */

public class ArrayQueue<T> implements Queue<T> {
    private Object [] queue;
    private int size;
    private int front;
    private int back;
    private int arrayCapacity;
    public static final int DEFAULT_CAPACITY = 25;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int initialCapacity) {
        arrayCapacity = initialCapacity;
        queue = new Object[initialCapacity];
    }

    @Override
    public boolean add(T obj) {
        if (size == arrayCapacity-1) { /**Why arraaycap-1, we want to have a wall inbetween the front and the back */
            resizeArray(arrayCapacity*2); /**Just resizes  */
        }
        queue[back] = obj;
        back = (back + 1)%arrayCapacity;
        size++;
        return true;
    }

    @Override
    public boolean offer(T obj) {
        try {
            return add(obj);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("Empty queue");
        }
    
        T val = (T) queue[front];
        queue[front] = null;
        front = (front + 1) % arrayCapacity; 
        size--;
        return val;
    }

    @Override
    public T poll() {
        try {
            return remove();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T element() {
        if (size == 0) {
            throw new NoSuchElementException("Empty queue");
        } 
        return (T)queue[front];
    }

    @Override
    public T peek() {
        try {
            return element();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    @Override
    public boolean contains(T obj) {
        for (int i = front; i != back; i=(i + 1)%arrayCapacity) {
            if(queue[i] == obj || queue[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        queue = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray(int newCapacity) {
        Object [] arr = new Object[newCapacity];
        int index = front;
        int i = 0;
        while (index != back) {
            arr[i] = queue[index];
            i++;
            index = (index + 1)%arrayCapacity;
        }
        front = 0;
        back = size;
        arrayCapacity = newCapacity;
    }

    @Override
    public Object[] toArray() {
        Object [] arr = new Object[size];
        for (int i = front, k =0; i != back; k++, i=(i + 1)%arrayCapacity) {
            arr[k] = queue[i];
        }
        return arr;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(T[] a) {
        return (T[])Arrays.copyOf(this.toArray(), size, a.getClass());
    }
    
}