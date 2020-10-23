package Drivers;

import java.util.EmptyStackException;
import Labs.VectorStack;

/**
 * Driver class for testing ArrayStack.
 * 
 * Yes, I'm going to upload this to Canvas.
 * 
 * @author Stephen J. Sarma-Weierman
 */
 public class StackDriver {
	public static void main(String [] args) {
		System.out.println("Attempting to create a new stack of integers...");
		long START_TIME = System.currentTimeMillis();
		VectorStack<Integer> stack = new VectorStack<>();
		System.out.print("Check to see if the stack is empty: ");
		if (stack.empty())
			System.out.println("PASS");
		else
			System.out.println("FAIL");
		System.out.print("Attempting to remove from empty stack (should throw EmptyStackException): ");
		try {
			System.out.println(stack.pop());
			System.out.println("FAIL");
		} catch (EmptyStackException e) {
			System.out.println("PASS");
		}
		




		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(null);
		stack.push(6);
		stack.push(7);
		stack.push(7);

		System.out.println("Should print 7: " + stack.peek());
		stack.pop();		//  removes 7
		System.out.println("Should print 1: " + stack.search(7));
		System.out.println("Should print 7: " + stack.peek());
		stack.pop(); 		//  remove 7
		System.out.println("Should return 2: "  + stack.search(null));
		System.out.println("Should return 1: " + stack.search(6));
		System.out.println("Should return 7: " + stack.search(1));
		stack.pop();		// remove 6
		stack.pop();		// remove null
		stack.pop();		// remove 5
		System.out.println("Should print 4: " + stack.peek());
		System.out.println("Should print -1: " + stack.search(null));
		stack.pop();		// remove 4
		stack.pop();		// remove 3
		stack.pop();		// remove 2
		stack.pop();		// remove 1
		System.out.println("Should print true: " + stack.empty());

		


		/**
		 * 
		 *  
		 * 
		 * 
		 * 
		 * */ 
		//TODO: Finish wiriting this driver


		long DELTA_TIME = System.currentTimeMillis() - START_TIME;
		System.out.println("Run-time (ms): "+DELTA_TIME+" ms");
	}
}
