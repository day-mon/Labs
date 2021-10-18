package Labs.AlgorithimAnaylsis.Sort;

import java.util.Arrays;
import java.util.Random;

public class SortTest
{
      public static void main(String[] args)
      {
            var arr = new int[20];
            int[] sArr = new int[20];
            int[] rArr = new int[20];

            for (int i = 0, k = 19; i < 20; i++, k--)
            {
                  sArr[i] = i;
                  rArr[i] = k;
            }
            var sort = new Sort();

           
            System.out.println("-----------Sorted Arrays-----------");
            System.out.println("Array before selection sort: " + Arrays.toString(sArr));
            sort.selection(sArr);
            System.out.println("Array after selection sort: " + Arrays.toString(sArr));
            System.out.println();

            System.out.println("Array before insertion sort: " + Arrays.toString(sArr));
            sort.insertion(sArr);
            System.out.println("Array after insertion sort: " + Arrays.toString(sArr));
            System.out.println();

            System.out.println("Array before bubble sort: " + Arrays.toString(sArr));
            sort.bubble(sArr);
            System.out.println("Array after bubble sort: " + Arrays.toString(sArr));
            System.out.println();

            System.out.println("Array before merge sort: " + Arrays.toString(sArr));
            sort.mergeSort(sArr);
            System.out.println("Array after merge sort: " + Arrays.toString(sArr));
            System.out.println();

            System.out.println("Array before quick sort(left) sort: " + Arrays.toString(sArr));
            sort.quickSort(sArr, 0, arr.length - 1, 1);
            System.out.println("Array after quick sort(left) sort: " + Arrays.toString(sArr));
            System.out.println();

            System.out.println("Array before quick sort(center) sort: " + Arrays.toString(sArr));
            sort.quickSort(sArr, 0, arr.length - 1, 2);
            System.out.println("Array after quick sort(center) sort: " + Arrays.toString(sArr));
            System.out.println();

            System.out.println("Array before quick sort(median 3) sort: " + Arrays.toString(sArr));
            sort.quickSort(sArr, 0, arr.length - 1, 4);
            System.out.println("Array after quick sort(median 3) sort: " + Arrays.toString(sArr));
            System.out.println();

            System.out.println("Array before quick sort(random) sort: " + Arrays.toString(sArr));
            sort.quickSort(sArr, 0, arr.length - 1, 3);
            System.out.println("Array after quick sort(random) sort: " + Arrays.toString(sArr));
            System.out.println();



                  // yes this is bad... 
         
            System.out.println("-----------Reverse Sorted Arrays-----------");
            var _1 = Arrays.copyOf(rArr, rArr.length);
            System.out.println("Array before selection sort: " + Arrays.toString(_1));
            sort.selection(_1);
            System.out.println("Array after selection sort: " + Arrays.toString(_1));
            System.out.println();

            var _2 = Arrays.copyOf(rArr, rArr.length);
            System.out.println("Array before insertion sort: " + Arrays.toString(_2));
            sort.insertion(_2);
            System.out.println("Array after insertion sort: " + Arrays.toString(_2));
            System.out.println();

            var _3 = Arrays.copyOf(rArr, rArr.length);
            System.out.println("Array before bubble sort: " + Arrays.toString(_3));
            sort.bubble(_3);
            System.out.println("Array after bubble sort: " + Arrays.toString(_3));
            System.out.println();

            var _4 = Arrays.copyOf(rArr, rArr.length);
            System.out.println("Array before merge sort: " + Arrays.toString(_4));
            sort.mergeSort(_4);
            System.out.println("Array after merge sort: " + Arrays.toString(_4));
            System.out.println();

            var _5 = Arrays.copyOf(rArr, rArr.length);
            System.out.println("Array before quick sort(left) sort: " + Arrays.toString(_5));
            sort.quickSort(_5, 0, arr.length - 1, 1);
            System.out.println("Array after quick sort(left) sort: " + Arrays.toString(_5));
            System.out.println();

            var _6 = Arrays.copyOf(rArr, rArr.length);
            System.out.println("Array before quick sort(center) sort: " + Arrays.toString(_6));
            sort.quickSort(_6, 0, arr.length - 1, 2);
            System.out.println("Array after quick sort(center) sort: " + Arrays.toString(_6));
            System.out.println();

            var _7 = Arrays.copyOf(rArr, rArr.length);
            System.out.println("Array before quick sort(median 3) sort: " + Arrays.toString(_7));
            sort.quickSort(_7, 0, arr.length - 1, 4);
            System.out.println("Array after quick sort(median 3) sort: " + Arrays.toString(_7));
            System.out.println();

            var _8 = Arrays.copyOf(rArr, rArr.length);
            System.out.println("Array before quick sort(random) sort: " + Arrays.toString(_8));
            sort.quickSort(_8, 0, arr.length - 1, 3);
            System.out.println("Array after quick sort(random) sort: " + Arrays.toString(_8));
            System.out.println();

         
            System.out.println("-----------Random Arrays-----------");

            arr = new Random().ints(20, 1, 2000).toArray();
            System.out.println("Array before selection sort: " + Arrays.toString(arr));
            sort.selection(arr);
            System.out.println("Array after selection sort: " + Arrays.toString(arr));
            System.out.println();

            //Insertion Sort
            arr = new Random().ints(20, 1, 2000).toArray();
            System.out.println("Array before insertion sort: " + Arrays.toString(arr));
            sort.insertion(arr);
            System.out.println("Array after insertion sort: " + Arrays.toString(arr));
            System.out.println();

            //Bubble Sort
            arr = new Random().ints(20, 1, 2000).toArray();
            System.out.println("Array before bubble sort: " + Arrays.toString(arr));
            sort.bubble(arr);
            System.out.println("Array after bubble sort: " + Arrays.toString(arr));
            System.out.println();

            //Merge Sort
            arr = new Random().ints(20, 1, 2000).toArray();
            System.out.println("Array before merge sort: " + Arrays.toString(arr));
            sort.mergeSort(arr);
            System.out.println("Array after merge sort: " + Arrays.toString(arr));
            System.out.println();

            //Quick Sort - Left
            arr = new Random().ints(20, 1, 2000).toArray();
            System.out.println("Array before quick sort(left) sort: " + Arrays.toString(arr));
            sort.quickSort(arr, 0, arr.length - 1, 1);
            System.out.println("Array after quick sort(left) sort: " + Arrays.toString(arr));
            System.out.println();

            //Quick Sort - Center
            arr = new Random().ints(20, 1, 2000).toArray();
            System.out.println("Array before quick sort(center) sort: " + Arrays.toString(arr));
            sort.quickSort(arr, 0, arr.length - 1, 2);
            System.out.println("Array after quick sort(center) sort: " + Arrays.toString(arr));
            System.out.println();

            //Quick Sort - Median of 3
            arr = new Random().ints(20, 1, 2000).toArray();
            System.out.println("Array before quick sort(median-3) sort: " + Arrays.toString(arr));
            sort.quickSort(arr, 0, arr.length - 1, 4);
            System.out.println("Array after quick sort(median-3) sort: " + Arrays.toString(arr));
            System.out.println();

            //Quick Sort - Random
            arr = new Random().ints(20, 1, 2000).toArray();
            System.out.println("Array before quick sort(random) sort: " + Arrays.toString(arr));
            sort.quickSort(arr, 0, arr.length - 1, 3);
            System.out.println("Array after quick sort(random) sort: " + Arrays.toString(arr));
            System.out.println();
      }
}
