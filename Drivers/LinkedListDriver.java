package Drivers;
import Labs.Other.LinkedList;


public class LinkedListDriver {

    public static void main(String[] args) {
        LinkedList<Integer> n = new LinkedList<>();

        for (int i = 0; i < 6; i++) {
            n.insertFront(i);
        } 

        n.print();

      

        
        String s = "fart";
        String d = "notfart";
        s = d;

        System.out.println(s);
        
    }

}
