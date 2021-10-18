package Labs.AlgorithimAnaylsis.Sort;

import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class Assign1
{
      final static int n = 35_000;

      public static void main(String[] args) throws InterruptedException
      {

            var map = Map.of(
                    1, "Left",
                    2, "Center",
                    3, "Random",
                    4, "Median"
            );

            Sort sort = new Sort();
            var random = new Random();


            for (var i = 1; i <= 8; i = 2 * i)
            {

                  var num = i * n;
                  var array = new int[(int) num];


                  for (var k = 1; k <= 3; k++)
                  {
                        System.out.println("---------------------------");

                        for (var c = 1; c <= 4; c++)
                        {
                              array = makeArray(k, num);
                              var sortedBefore = isSorted(array);

                              if (k == 1)
                              {
                                    var START_TIME = System.currentTimeMillis();
                                    sort.quickSort(array, 0, array.length - 1, c);
                                    var DELTA_TIME = System.currentTimeMillis() - START_TIME;
                                    System.out.println(map.get(c).toUpperCase() + " PIVOT FOR SIZE " + num + ": " + DELTA_TIME + " ms | " + " Sorted: " + isSorted(array) + " | " + "Array type: Sorted " + " | Sorted before: " + sortedBefore);

                              }
                              else if (k == 2)
                              {
                                    var START_TIME = System.currentTimeMillis();
                                    sort.quickSort(array, 0, array.length - 1, c);
                                    var DELTA_TIME = System.currentTimeMillis() - START_TIME;
                                    System.out.println(map.get(c).toUpperCase() + " PIVOT FOR SIZE " + num + ": " + DELTA_TIME + " ms | " + " Sorted: " + isSorted(array) + " | " + "Array type: Random " + " | Sorted before: " + sortedBefore);
                              }
                              else
                              {
                                    var START_TIME = System.currentTimeMillis();
                                    sort.quickSort(array, 0, array.length - 1, c);
                                    var DELTA_TIME = System.currentTimeMillis() - START_TIME;
                                    System.out.println(map.get(c).toUpperCase() + " PIVOT FOR SIZE " + num + ": " + DELTA_TIME + " ms | " + " Sorted: " + isSorted(array) + " | " + "Array type: Reverse " + " | Sorted before: " + sortedBefore);
                              }
                        }
                  }
            }
      }


      public static int[] makeArray(int iT, int size)
      {
            var random = new Random();
            if (iT == 1)
            {
                  return random.ints(size, 0, 100000)
                          .boxed() // need to use non-primitive version
                          .sorted()
                          .mapToInt(Integer::intValue)
                          .toArray();
            }
            else if (iT == 2)
            {
                  return random.ints(size, 0, 100000).toArray();
            }
            else
            {
                  return random.ints(size, 0, 100000)
                          .boxed() // need to use non-primitive version
                          .sorted(Collections.reverseOrder())
                          .mapToInt(Integer::intValue)
                          .toArray();
            }


      }

      public static boolean isSorted(int[] array)
      {
            for (int i = 0; i < array.length - 1; i++)
            {
                  if (array[i] > array[i + 1])
                        return false;
            }
            return true;
      }


}
