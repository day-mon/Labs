package Drivers;


import Labs.CS_0045_DSAINFO.AutoSorter;

public class AutoSorterDriver {
    public static void main(String[] args) {
        long START_TIME = System.currentTimeMillis();
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        AutoSorter<String> names = new AutoSorter<String>();
        names.add("Frank");
        names.add("Eve");
        names.add("Alice");
        names.add("Carol");
        names.add("Bob");
        names.add("David");
        names.add("Eugene");
        

        System.out.println(names.toArray());

        
        if (!names.isEmpty())
            System.out.println("Names is not empty");
        for (String name : names.toArray(new String[0])) {
            System.out.println(name);
        }
        if (names.contains("Bob")) 
            System.out.println("Contains Bob");
        if (!names.contains("Steve"))
            System.out.println("Does not contain Steve");
        System.out.println("Size: " + names.size());
        
        if (names.remove("Eugene"))
            System.out.println("Removed Eugene");
        System.out.println("Size: " + names.size());
        for (String name : names.toArray(new String[0])) {
            System.out.println(name);
        }
        
        if (!names.remove("Eugene"))
            System.out.println("Could not remove Eugene again");
        System.out.println("Size: " + names.size());
        System.out.println("Clear all");
        names.clear();
        System.out.println("Size: " + names.size());
        if (names.isEmpty())
            System.out.println("Names is empty");/**/
        long DELTA_TIME = System.currentTimeMillis() - START_TIME;
        double afterUsedMem=(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()) / 1048576;
        String usage = afterUsedMem > 1 ?  " bytes" : " byte";
        
        System.out.println("Memory usage (bytes): "+afterUsedMem+usage);
        System.out.println("Run-time (ms): "+DELTA_TIME+" ms");
    }
    
}
