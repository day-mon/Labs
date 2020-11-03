package Drivers;

// import java.util.Arrays; // Totally unnecessary line again
import Interfaces.Set;
import Labs.LinkedSet;

/**
 * Driver method for testing the ArraySet data structure.
 * @author sjw
 */
public class SetDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Creating set of Integers...");
        long START_TIME = System.currentTimeMillis();
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        Set<Integer> s = new LinkedSet<>();
        System.out.println("Adding values to set...");
        s.add(42);
        s.add(3);
        s.add(27);
        s.add(99);
        s.add(50);
        s.add(75);
        s.add(-1);
        s.add(32);
        System.out.println("Attempting duplicate add (should be false): " + s.add(-1));
        System.out.println("Checking size (should be 8): " + s.size());
        System.out.println("Checking contents of set (should be [42 3 27 99 50 75 -1 32] in no particular order): ");
        for (Object i : s.toArray())
            System.out.print(i + " ");
        System.out.println();
        System.out.println("Testing remove of -3 (should return false): " + s.remove(-3));
        System.out.println("Checking size (should be 8): " + s.size());
        System.out.println("Checking contains of -3 (should return false): " + s.contains(-3));
        System.out.println("Checking contains of 42 (should return true): " + s.contains(42));
        System.out.println("Checking contains of 99 (should return true): " + s.contains(99));
        System.out.println("Checking contains of 32 (should return true): " + s.contains(32));
        System.out.println("Testing remove of 99 (should return true): " + s.remove(99));
        System.out.println("Checking size (should be 7): " + s.size());
        System.out.println("Checking contains of 99 (should return false): " + s.contains(99));
        System.out.println("Testing remove of 99 (should return false): " + s.remove(99));
        System.out.println("Checking size (should be 7): " + s.size());
        System.out.println("Testing remove of 42 (should return true): " + s.remove(42));
        System.out.println("Checking size (should be 6): " + s.size());
        System.out.println("Checking contains of 42 (should return false): " + s.contains(42));
        System.out.println("Testing remove of 32 (should return true): " + s.remove(32));
        System.out.println("Checking size (should be 5): " + s.size());
        System.out.println("Checking contains of 32 (should return false): " + s.contains(32));
        System.out.println("Checking contents of set (should be [3 27 50 75 -1] in no particular order): ");
        for (Object i : s.toArray())
            System.out.print(i + " ");
        System.out.println();
        int removed;
        System.out.println("Remove any one element: " + (removed = s.remove()));
        System.out.println("Checking contents of set (should be [3 27 50 75 -1] without "+removed+"): ");
        for (Object i : s.toArray())
            System.out.print(i + " ");
        System.out.println();
        System.out.println("Checking size (should be 4): " + s.size());
        System.out.println("Testing clear...");
        s.clear();
        System.out.println("Checking size (should be 0): " + s.size());
        for (Object i : s.toArray())
            System.out.print(i + " ");
            
            long DELTA_TIME = System.currentTimeMillis() - START_TIME;
            double afterUsedMem=(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()) / 1048576;
            String usage = afterUsedMem > 1 ?  " bytes" : " byte"; 
            System.out.println("Run-time (bytes): "+afterUsedMem+usage);
            System.out.println("Run-time (ms): "+DELTA_TIME+" ms");
    }
    
}
