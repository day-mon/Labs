package Labs;

import java.util.Arrays;
import Interfaces.Set;

/**
 * An implementation of Set using arrays. This implementation should 
 * resize the array if the set reaches capacity.
 * @author sjw
 * @author Damon L. Montague Jr
 *
 * key takeaways:
 * size and length arent the same thing
 * size = current size of array
 * capcity = maxiumn objects allowed.
 *
 * if a part of the arrayset was null, that is considered to not be included in the size.
 * so for every null you subtract 1 from the size.
 *
 * remove method was intestingly changellening and took me a while because i didnt understand that
 * concept. because you set all of the elements to null the size is supposed to be 0. because nulls arent counted
 *
 * when using Arrays.copyOf, it will get rid of every null value, in the prevoius array.
 */

public class ArraySet<T> implements Set<T> {
    private static final int DEFAULT_CAPACITY = 25;
    private Object[] set;
    private int size;
    private int capacity;
    
    public ArraySet() {
        this(DEFAULT_CAPACITY);
    }
    
    //TODO: initialize capacity field, initialize the set to be a new Object array of size capacity
    public ArraySet(int capacity) {
        this.capacity= capacity;
        set = new Object[capacity];
    }

    //TODO: check to see if the obj is null, or if it is already in the set: return false if either is true
    //check to see if the array is at capactiy, if it is, resize the array
    //add the object to the next free entry in the array, increment size, and return true
    @Override
    public boolean add(T obj) {
        if (obj == null || this.contains(obj)) {
            return false;
        }

        if (isArrayFull()) {
            resizeArray(capacity * 2);
        }

        set[size++] = obj;
        // this will add the generic to the end of the array then increment the size by 1.
        return true;
        
    }

    //TODO: check to see if obj is in the collection, if it is not, return false
    //otherwise, use the index of the object to remove the object by replacing the last object in the array 
    // in its position. Decrement size. return true
    @Override
    public boolean remove(T obj) {
        for (int i = 0; i < size - 1; i++) {
            if (set[i] == obj) { // finds object in set
                set[i] = set[size-1]; // takes last element and puts it in current position
                set[size-1] = null; // sets last position to null
                size--; // decrements size of array by one
                return true;
            }
        }
        return false;

    }

    //If the array contains at least one object, remove and return a reference to the last object in the array. Otherwise, return null.
    @Override
    public T remove() {
        if (size == 0)
            return null;
        T obj = (T)set[--size];
        set[size] = null;
        return obj;
    }

    //TODO: Determine if the collection contains obj
    @Override
    public boolean contains(T obj) {
        for (int i = 0; i < size; i++) {
            if (set[i] ==  obj || set[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }

    //TODO: determine if the collection is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //TODO: remove all the objects in the array, set size to 0
    @Override
    public void clear() {
        for (int i = size -1; i > size; i--) {
            set[i] = null;
        }
        size = 0;
    }

    //TODO: get the size of the collection
    @Override
    public int size() {
        return size;
    }

    //TODO: return a new array containing all the elements in the collection.
    @Override
    public Object[] toArray() {
      return Arrays.copyOf(set, size);
        
    }
    
    //TODO: find the occurance of obj in the array, and return the corresponding index. If obj is not in the array, return -1
    private int indexOf(T obj) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (set[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }
    
    //TODO: Determine if the array is at capacity.
    private boolean isArrayFull() {
        return size == capacity;
    }
    
    private boolean resizeArray(int newCapacity) {
        //Don't let this method shrink the array smaller than the number of items in the set
        if (newCapacity < size)
            return false;
        capacity = newCapacity;
        set = Arrays.copyOf(set, newCapacity);
        return true;
    }
}
