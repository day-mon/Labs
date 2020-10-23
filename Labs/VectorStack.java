package Labs; // Organize files better, this shouldn't be in drivers, even if it isn't a lab.
              // In that case, move it to a "utils" or "helpers" pacakge. It's better 
              // coding practices. Even if professors don't care about that, everyone else does.

import java.util.Vector;
import java.util.EmptyStackException;
import Interfaces.Stack;

public class VectorStack<T> implements Stack<T> {

    private Vector<T> stack;

    public VectorStack() {
        stack = new Vector<T>();
    }

    /**
     * Adds something to the top of the stack.
     * @param obj - the object to be added.
     */
    public T push(T obj) {
        stack.add(obj);
        return obj;
    }

    /**
     * Returns a refernce to the top of the stack.
     * Does not modify the stack.
     * @return a reference to the top of the stack.
     */
    public T peek() {
        if (empty())
            throw new EmptyStackException();
        return stack.get(stack.size()-1);
    }

    /**
     * Removes the top element from the stack.
     * @return a reference to the element that was on the top of the stack.
     */
    public T pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        T rem = stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        return rem;

    }

    /**
     * Determines if the stack is empty.
     * @return true if the stack is empty.
     */
    public boolean empty()
    {
        return stack.isEmpty();
    }

    public int search(T obj) {  
       int index = stack.lastIndexOf(obj);
       if (index == -1) {
           return -1;
       } else {
           return index;
       }
    }
}

