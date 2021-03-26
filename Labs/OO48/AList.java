package Labs.OO48;

import Interfaces.List;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * An array-based list data structure. For this implementation, please use the
 * inculded List.java interface, and <b>not</b> java.util.List.
 *
 * @author Stephen J. Weierman
 * @author YOUR NAME HERE
 */
public class AList<T> implements List<T>
{
    private int size;
    private Object[] list;
    private int capacity;
    public static final int DEFAULT_CAPACITY = 25;

    //DONE
    public AList()
    {
        this(DEFAULT_CAPACITY);
    }

    //TODO: Set the capacity field, and create the list array field of size capacity
    public AList(int initialCapacity)
    {
        list = new Object[initialCapacity];
        capacity = initialCapacity;
    }

    //TODO: Check the index to make sure the value is in [0, size]
    //Check if the size of the array is at capacity, and resize if necessary
    //Shift all values after index to create a space for obj
    //place obj at index in the list array
    //return true
    public boolean add(int index, T obj)
    {
        if (size == capacity)
        {
            resizeArray(capacity * 2);
        }
        if (index > size || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }

        for (int i = size; i > index; i--)
        {
            list[i] = list[i - 1];
        }

        list[index] = obj;
        size++;
        return true;

    }

    //TODO: Check if the size of the array is at capacity, and resize if necessary
    //Place obj at index size in the list array
    //increment size
    //return true
    public boolean add(T obj)
    {
        if (size == capacity)
        {
            resizeArray(2 * capacity);
        }
        list[size++] = obj;
        return true;
    }

    //TODO: Check the index to make sure index is in [0, size)
    //Return list[index] if valid index, throw IndexOutOfBoundsException otherwise
    @SuppressWarnings("unchecked")
    public T get(int index)
    {
        if (index > size || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        return (T) list[index];
    }

    //TODO: If obj is contained in the List, return the index of the first occurrence.
    //Return -1 otherwise. 
    public int indexOf(T obj)
    {
        for (int i = 0; i < size; i++)
        {
            if (list[i].equals(obj))
            {
                return i;
            }
        }
        return -1;
    }

    //TODO: If obj is contained in the List, return the index of the last occurrence.
    //Return -1 otherwise.
    public int lastIndexOf(T obj)
    {
        int index = -1;
        for (int i = 0; i < size; i++)
        {
            if (list[i].equals(obj))
            {
                index = i;
            }
        }
        return index;
    }

    // DONE ************** DO NOT MODIFY LIST ITERATOR CODE *************** //
    public ListIterator<T> listIterator()
    {
        return new ListIterator<T>()
        {
            static final int PREVIOUS = 1;
            static final int NEXT = 2;
            int lastCalled = 0;
            //LastCalled indicates if next or previous have been called.
            int nextIndex = 0;

            @Override
            public boolean hasNext()
            {
                return nextIndex < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException("End of collection");
                }
                lastCalled = NEXT;
                return (T) (list[nextIndex++]);
            }

            @Override
            public boolean hasPrevious()
            {
                return nextIndex > 0;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T previous()
            {
                if (!hasPrevious())
                {
                    throw new NoSuchElementException("Start of collection");
                }
                lastCalled = PREVIOUS;
                return (T) list[--nextIndex];
            }

            @Override
            public int nextIndex()
            {
                return nextIndex;
            }

            @Override
            public int previousIndex()
            {
                return nextIndex - 1;
            }

            @Override
            public void remove()
            {
                if (lastCalled == PREVIOUS)
                { //Remove current postiion
                    AList.this.remove(nextIndex);
                }
                else if (lastCalled == NEXT)
                { //Remove last position
                    AList.this.remove(--nextIndex);
                }
                else
                {
                    throw new IllegalStateException("removed called without next or previous");
                }
                lastCalled = 0; //Reset last called to prevent repeated calls.
            }

            @Override
            public void set(T e)
            {
                if (lastCalled == PREVIOUS)
                {
                    list[nextIndex] = e;
                }
                else if (lastCalled == NEXT)
                {
                    list[nextIndex - 1] = e;
                }
                else
                {
                    throw new IllegalStateException("set called without next or previous");
                }

            }

            @Override
            public void add(T e)
            {
                //Insert before next
                AList.this.add(nextIndex++, e);
            }

        };
    }
    // ************** DO NOT MODIFY ABOVE CODE *************** //

    //DONE: Check the index to make sure index is in [0, size)
    //If it isn't, throw an IndexOutOfBoundsException
    //If it is, get the object at position index, move every object
    //after that index down by one.
    //Place null in position size-1 to dereference
    //Decrement size and return the object
    public T remove(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        T obj = get(index);
        for (int i = index; i < size - 1; i++)
        {
            list[i] = list[i + 1];
        }
        list[--size] = null;
        return obj;
    }

    //TODO: Check the index to make sure index is in [0, size)
    //If it isn't, throw an IndexOutOfBoundsException
    //If it is, get the original value at position index,
    //Place the new obj into the array at index
    //Return the original object
    public T set(int index, T obj)
    {
        if (size == capacity)
        {
            resizeArray(2 * capacity);
        }
        if (index > size || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        T objAtIndex = (T) list[index];

        list[index] = obj;

        return objAtIndex;
    }

    //TODO: You should know how to do this by now.
    public boolean contains(T obj)
    {
        return indexOf(obj) >= 0;
    }

    //TODO: You should know how to do this by now.
    public int size()
    {
        return size;
    }

    //TODO: You should know how to do this by now.
    public void clear()
    {
        list = new Object[capacity];
    }

    //TODO: You should know how to do this by now.
    public boolean isEmpty()
    {
        return size == 0;
    }

    //DONE
    public Object[] toArray()
    {
        return Arrays.copyOf(list, size);
    }

    //DONE
    @SuppressWarnings("unchecked")
    public T[] toArray(T[] a)
    {
        return (T[]) Arrays.copyOf(list, size, a.getClass());
    }

    private void resizeArray(int newCapacity)
    {
        list = Arrays.copyOf(list, newCapacity);
        capacity = newCapacity;
    }
}
