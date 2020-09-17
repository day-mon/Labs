
import java.util.Arrays;

/**
 * An implementation of Set using arrays. This implementation should 
 * resize the array if the set reaches capacity.
 * @author sjw
 * @author Ryan Leitenberger
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
        this.capacity = capacity;
        set = new Object[capacity];
    }

    //TODO: check to see if the obj is null, or if it is already in the set: return false if either is true
    //check to see if the array is at capactiy, if it is, resize the array
    //add the object to the next free entry in the array, increment size, and return true
    @Override
    public boolean add(T obj) {
        if (obj == null || this.contains(obj)){
            return false;
        }
        if (isArrayFull()){
            resizeArray(capacity * 2);
        }
        set[size++] = obj;
        return true;
    }

    //TODO: check to see if obj is in the collection, if it is not, return false
    //otherwise, use the index of the object to remove the object by replacing the last object in the array
    // in its position. Decrement size. return true
    @Override
    public boolean remove(T obj) {
        int index = indexOf(obj);
        if (index == -1)
            return false;
        set[index] = set[size-1];
        size--;
        return true;
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
        if (obj == null){
            return false;
        }
        for (int i = 0; i < size; i++){
            if(set[i].equals(obj)){
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
        set = new Object[capacity];
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
        Object [] result = new Object [size];
        for (int i = 0; i < size; i++){
            result[i] = set[i];
        }
        return result;
    }

    //TODO: find the occurance of obj in the array, and return the corresponding index. If obj is not in the array, return -1
    private int indexOf(T obj) {
        int index = 0;
        boolean found = false;
        while(index < size && !found){
            if (set[index].equals(obj)){
                found = true;
                break;
            }
            else {
                index++;
            }
        }
        return found?index: -1;
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