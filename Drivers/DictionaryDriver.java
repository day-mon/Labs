package Drivers;

import Interfaces.Dictionary;
import Labs.OO48.HashDictionary;

import java.util.Iterator;

public class DictionaryDriver {

    /**
     * HashDictionary driver
     *
     * @param args
     */
    public static void main(String[] args)
    {
        long START_TIME = System.currentTimeMillis();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        Dictionary<String, Integer> dict = new HashDictionary<>(7);

        System.out.println("Adding things...");

        dict.put("apple", 5);
        dict.put("hello", 2);
        dict.put("coffee", 6);
        dict.put("coffee", 3);
        dict.remove("apple");
        dict.put("apple", 1);
        dict.put("APPLE", 5);
        dict.put("HELLO", 2);
        dict.put("COFFEE", 6);
        dict.put("java", 5);
        dict.put("tea", 2);
        dict.put("water", 6);
        dict.put("bob", 5);
        dict.put("alice", 2);
        dict.put("eve", 6);
        System.out.println("size = " + dict.size());

        System.out.println("Testing iterators...");

        //Testing iterators
        Iterator<String> keyIt = dict.keys();
        Iterator<Integer> valIt = dict.elements();
        while(keyIt.hasNext())
        {
            if (!valIt.hasNext())
            {
                System.out.println("Problem with iterator, more keys than values");
            }
            String k = keyIt.next();
            int v = valIt.next();
            if (v == dict.get(k))
            {
                System.out.printf("(%s, %d) in dictionary\n", k, v);
            }
            else
            {
                System.out.println("Problem with iterators, key-value pair not matching.");
            }

        }
        if (valIt.hasNext())
        {
            System.out.println("Problem with iterator, more values than keys");
        }
        long DELTA_TIME = System.currentTimeMillis() - START_TIME;
        double afterUsedMem = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
        String usage = afterUsedMem > 1 ? " bytes" : " byte";
        System.out.println("Memory usage (bytes): " + afterUsedMem + usage);
        System.out.println("Run-time (ms): " + DELTA_TIME + " ms");
    }

}
