public class AutoSorterDriver {
    public static void main(String[] args) {
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
    }
    
}
