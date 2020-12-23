package Labs.Other;

import java.util.EmptyStackException;

public class ArrayStack<T> {
   private Object[] items;
   private int top;
   private int capcity;
   public static final int DEFAULT_CAPACITY = 25;


   public ArrayStack() {
       this(DEFAULT_CAPACITY);
   }

   public ArrayStack(int capcity) {
       this.capcity = capcity;

   }
   /*
        Time complexity: O(1)
   */
   public void push(T val) {
        if (top == capcity) {
            throw new IndexOutOfBoundsException("Stack is full");
        } else {
            items[top-1] = val;
            top+=1;
        }
   }

   /*
        Time complexity: O(1)
   */
   public T pop() {

       if (top == 0) {
           throw new EmptyStackException();
       } else {
        T val = (T)items[top-1];
        items[top-1] = null;
        top-=1;
        return val;
       }
   }

   /*
        Time complexity: O(1)
   */
   public T peek() {
       if (top == 0) {
           throw new EmptyStackException();
       } else {
           return (T)items[top-1];
       }
   }

    /*
        Time complexity: O(1)
   */
   public boolean isEmpty() {
       return top == 0;
   }
}
