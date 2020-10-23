package Interfaces;
/**
 * This is a simplified interface for Queue, based on the Java API.
 * See <a href="https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/Queue.html">this documentation</a>
 * for more details.
 * 
 * ***********************************************
 * *** TODO: FILL IN THE MISSING DOCUMENTATION ***
 * ***********************************************
 * 
 * @author Stephen J. Sarma-Weierman
 * @author YOUR NAME HERE
 * @version 1.0
 */
public interface Queue<T> {
	
	/**
	 * See the documentation linked above for more details. Instead of throwing an exception
	 * if the maximum capacity is exceeded, we'll return false (if there is a capacity restriction).
	 * @param obj - object to be added.
	 * @return true if successful.
	 */
	public boolean add(T obj);

	/**
	 * 
	 */
	public boolean offer(T obj);

	/**
	 * 
	 */
	public T remove();

	/**
	 * 
	 */
	public T poll();
	
	/**
	 * 
	 */
	public T element();
	
	/**
	 * 
	 */
	public T peek();

	/**
	 * 
	 */
	public boolean contains(T obj);
	
	/**
	 * 
	 */
	public int size();
	
	/**
	 * 
	 */
	public void clear();
	
	/**
	 * 
	 */
	public boolean isEmpty();
	
	/**
	 * 
	 */
	public Object [] toArray();

	/**
	 * 
	 */
	public T[] toArray(T[] a);
}