import java.util.ListIterator;

public class ListDriver {
    public static void main(String[] args) {
        List<String> names = new AList<>(2);
        names.add("Alice");
        names.add("Carol");
        names.add(1, "Bob");
        names.add("David");
        names.add("Eve");
        names.add("Bob");
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println("index of Bob (should be 1): " + names.indexOf("Bob") );
        System.out.println("last index of Bob (should be 5): " + names.lastIndexOf("Bob"));
        System.out.println("contains Eve (should be true): " + names.contains("Eve"));
        System.out.println("contains Jack (should be false): " + names.contains("Jack"));
        System.out.println("Removing value at index 1 (Bob)...");
        names.remove(1);
        System.out.println("index of Bob (should be 4): " + names.indexOf("Bob") );
        System.out.println("last index of Bob (should be 4): " + names.lastIndexOf("Bob"));
        System.out.println("contains null (should be false): " + names.contains(null));

        System.out.println("Test ListIterator");
        ListIterator<String> it = names.listIterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("Backwards...");
        while (it.hasPrevious()) {
            System.out.println(it.previous());
        }
        System.out.println("next should return Alice: " + it.next());
        System.out.println("next should return Carol: " + it.next());
        it.remove();
        System.out.println("remove should remove Carol. Previous should return Alice: " + it.previous());
        it.remove();
        System.out.println("Foreach over collection.");
        for(String name: names) {
            System.out.println(name);
        }
    }
    
}
