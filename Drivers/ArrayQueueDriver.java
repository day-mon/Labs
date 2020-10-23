package Drivers;

import java.util.HashMap;
import Labs.ArrayQueue;

public class ArrayQueueDriver {
    public static void main(String[] args) {
        System.out.println("Attempting to create a new queue of integers...");
        ArrayQueue<Integer> s = new ArrayQueue<Integer>();

        for (int i=0;i<23;i++) 
            s.add(i);

        
        System.out.println("Checking top should be 0: " + s.element());
        System.out.println("Checking top should be 0: " + s.peek());
        System.out.println("Checking remove of 0 should be 0: " + s.remove());
        System.out.println("Checking remove of 1 should be 1: " + s.poll());
        System.out.println("Searching for 1 (should be false): " + s.contains(0));
        System.out.println("Checking size (should be 21): " + s.size());

        for (Object x : s.toArray())
            System.out.print(x + " ");
        
        System.out.println("\nSearching 1->25: " );

        for (int i = 0; i < 25; i++)
            System.out.println(i + ": " + s.contains(i));

        s.clear();
        System.out.println("Checking size (should be 0): " + s.size());
        try {
            s.remove();
        } catch (Exception e) {
            System.out.println("Sucess!");
        }
        System.out.println("Checking testing poll (should be null): " + s.poll());
        System.out.println("Thats all goodbye for now!");
        
    }
}