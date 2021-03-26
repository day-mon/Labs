package Labs.OO45;

import java.util.Arrays;
import java.util.Vector;

import Labs.OO45.VectorStack;

/**
 * Container class for storing and autosorting values in ascending order.
 * The top of the store stack will contain the lowest value in the collection. The bottom will
 * contain the highest value. When a new value is added to the collection, values are popped from
 * store to temp until the appropriate position is found.
 */
public class AutoSorter<T extends Comparable<? super T>>
{
    private VectorStack<T> store;
    private VectorStack<T> temp;
    private int size;

    public AutoSorter()
    {
        store = new VectorStack<>();
        temp = new VectorStack<>();
    }

    public boolean add(T obj)
    {
        if (obj == null)
        {
            return false;
        }


        while (!store.empty() && store.peek().compareTo(obj) < 0)
        {
            temp.push(store.pop());
        }
        store.push(obj);
        while (!temp.empty())
        {
            store.push(temp.pop());
        }
        size++;
        return true;
    }

    public boolean remove(T obj)
    {
        if (obj == null)
        {
            return false;
        }

        if (store.search(obj) == -1)
        {
            return false;
        }

        while (!store.empty() && !store.peek().equals(obj))
        {
            temp.push(store.pop());
        }
        store.pop();
        while (!temp.empty())
        {
            store.push(temp.pop());
        }
        size--;
        return true;
    }

    public boolean contains(T obj)
    {
        return store.search(obj) >= 0;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public void clear()
    {
        store = new VectorStack<>();
        size = 0;
    }

    public Object[] toArray()
    {
        Object[] arr = new Object[size];


        for (int i = 0; i < size; i++)
        {
            temp.push(store.peek());
            arr[i] = store.peek();
            store.pop();
        }

        while (!temp.empty())
        {
            store.push(temp.pop());
        }

        return arr;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(T[] a)
    {
        return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
    }
}
