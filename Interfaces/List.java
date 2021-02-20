import java.util.Iterator;
import java.util.ListIterator;

/**
 * List interface for Data Structures and Files.
 * Use this interface for both AList and LinkedList implementation.
 * @author Stephen J. Weierman
 */
public interface List<T> extends Iterable<T> {
    public boolean add(int index, T obj);
    public boolean add(T obj);
    public T get(int index);
    public int indexOf(T obj);
    public int lastIndexOf(T obj);
    public ListIterator<T> listIterator();
    public default Iterator<T> iterator() {
        return listIterator();
    }
    public T remove(int index);
    public T set(int index, T obj);
    public boolean contains(T obj);
    public int size();
    public void clear();
    public boolean isEmpty();
    public Object[] toArray();
    public T[] toArray(T[] a);
}
