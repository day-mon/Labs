package Labs.CS_1760_AOOP.Assign1;

import java.util.List;

public class Assign2Driver
{
      public static void main(String[] args)
      {
            Volume1 a, b, c, d, e, f, g, h;
            Volume2 w, x, y, z;
            a = new Volume1(114, 37);
            b = new Volume1(56, 41);
            w = new Volume2(57, 38 , 6);
            x = new Volume2(56, 41, 7);
            c = d = new Volume1();
            g = f = e = z = y = new Volume2();


            c = a.add(b);
            d = a.subtract(b);
            y = w.add(x);
            z = w.subtract(x);
            e = a.add(w);
            f = a.subtract(w);
            g = x.add(b);
            h = x.subtract(b);


            var list = List.of(a, b, c, d, e, f, g, h, w, x, z, y);

            System.out.println("Starting Test.....");

            list.stream()
                    .peek(element -> System.out.printf("Unsorted %s%n", element))
                    .sorted()
                    .forEach(element -> System.out.printf("Sorted %s%n", element));

            System.out.printf("""

                    Other Test
                    b.compareTo(x) == %d
                    x.compareTo(b) == %d
                    Print a in pints == %d
                    Print w in pints == %d
                    """, b.compareTo(x), x.compareTo(b), a.toPints(), w.toPints());
      }

}
