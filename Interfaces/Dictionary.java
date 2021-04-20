package Interfaces;

import java.util.Iterator;

/**
 * Interface for Dictionary ADT. Your HashDictionary class should implement
 * this interface.
 *
 * @author Stephen J. Sarma-Weierman
 */
public interface Dictionary<K, V>
{
    /**
     * This returns an iterator over the collection of keys.
     *
     * @return key iterator
     */
    public Iterator<K> keys();

    /**
     * This returns an iterator over the collection of values.
     *
     * @return values iterator
     */
    public Iterator<V> elements();

    /**
     * If the key is in the collection of keys, return the associated
     * value. Otherwise, return null.
     * Precondition: key != null
     *
     * @param key
     * @return value associated with key
     */
    public V get(K key);

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
    public V remove(K key);

    /**
     * If the key is in the collection of keys, update the associated
     * value, and return the previous value associated with key. If the
     * key is not in the collection, add both key and value to the collection
     * and return null.
     * Preconditions: key != null, value != null
     * Postcondition: (key, value) pair is in the dictionary
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value);

    /**
     * You know what this does.
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * You know what this does.
     *
     * @return
     */
    public int size();
}
