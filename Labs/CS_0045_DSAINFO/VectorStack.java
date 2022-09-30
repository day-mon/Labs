package Labs.CS_0045_DSAINFO;

import Interfaces.Stack;

import java.util.Vector;
import java.util.EmptyStackException;

public class VectorStack<T> implements Stack<T>
{

    private Vector<T> stack;

    public VectorStack()
    {
        stack = new Vector<T>();
    }

    /**
     * Adds something to the top of the stack.
     *
     * @param obj - the object to be added.
     */
    public T push(T obj)
    {
        stack.add(obj);
        return obj;
    }

    /**
     * Returns a refernce to the top of the stack.
     * Does not modify the stack.
     *
     * @return a reference to the top of the stack.
     */
    public T peek()
    {
        if (empty())
        {
            throw new EmptyStackException();
        }
        return stack.get(stack.size() - 1);
    }

    /**
     * Removes the top element from the stack.
     *
     * @return a reference to the element that was on the top of the stack.
     */
    public T pop()
    {
        if (empty())
        {
            throw new EmptyStackException();
        }
        T rem = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return rem;

    }

    /**
     * Determines if the stack is empty.
     *
     * @return true if the stack is empty.
     */
    public boolean empty()
    {
        return stack.isEmpty();
    }

    /**
     * Uses Vectors lastIndexOf method to find the last index of T.
     * Due to the nature of the data structure to find the index,
     * we will need to subtract the index and the size of the stack
     *
     * @param obj
     * @return -1 if isnt found; index if found.
     */
    public int search(T obj)
    {
        int index = stack.lastIndexOf(obj);
        return index < 0 ? -1 : stack.size() - index;
    }
}