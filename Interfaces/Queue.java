/**
 * This is a simplified interface for Queue, based on the Java API.
 * See <a href="https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/Queue.html">this documentation</a>
 * for more details.
 * 
 * 
 * @author Stephen J. Sarma-Weierman
 * @author Damon L. Montague Jr.
 * @version 1.0
 */
public interface Queue<T> {
	
	/**
	 * See the documentation linked above for more details. Instead of throwing an exception
	 * if the maximum capacity is exceeded, we'll return false (if there is a capacity restriction).
	 * 
	 * @param obj  object to be added.
	 * @return {@code true} if object was added, else
	 * 		   {@code false} if object wasnt added
	 */
	public boolean add(T obj);

	/**
	 * Will attempt to add element if possible by not violating the capcity rules.
	 * Method is preferred when dealing with queues that dont have a capcity for 
	 * fact that {@link #add}, will throw exceptions.
	 * 
	 * @param obj  object to be added
	 * @return {@code true} if object is added, else
	 * 		   {@code false} if object cant be added																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																											
	 *
	 */
	public boolean offer(T obj);

	/**
	 * Removes from the front of the queue 
	 * 
	 * @return the front of the queue
	 * @throws NoSuchElementException if queue is empty 
	 */
	public T remove();

	/**
	 *  Removes from the front of the queue.
	 *  This method differs from {@link #remove()} in that it will not throw an exception
	 * 
	 * @return he front of the queue, or null if queue is empty 
	 * 
	 */
	public T poll();
	
	/**
	 * Grabs but doesnt remove the front of the queue.
	 * 
	 * @return the head of the queue
     * @throws NoSuchElementException if this queue is empty
	 */
	public T element();
	
	/**
	 * Grabs but doesnt remove the front of the queue.
	 * This method differs fron {@link #element()} only in that it will not throw an exception, 
	 * instead it will return null if the queue is empty.
	 * 
	 * @return the front of the queue, or null if its empty
	 */
	public T peek();

	/**
	 * Scans entire collection to check for the element.
	 * 
	 * @param obj the element thats presence is questioned
	 * @return {@code true} if element is found within collection, 
	 * 		   {@code false} if element is not in collectio n
	 */
	public boolean contains(T obj);
	
	/**
	 * Returns the number of elements in queue
	 * 
	 * @return the number of elements in queue
	 */
	public int size();
	
	/**
	 * Removes all elements from the queue
	 */
	public void clear();
	
	/**
	 * Checks if queue has elements remaining
	 * 
	 * @return {@code true} if queue has no elements remaining inside, else
	 * 		   {@code false}
	 */
	public boolean isEmpty();
	
	/**
	 * Returns an array of all elements in correct order (front to back)
	 * 
	 * @return an array containing all elements
	 */
	public Object [] toArray();

	/**
	 * Returns an array of all elements in correct order (front to back)
	 * This array will be of type T
	 *
	 * @return an array containing all elements
	 */
	public T[] toArray(T[] a);
}