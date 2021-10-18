package Labs.Other;

public class sort
{
      public static void main(String[] args)
      {

            int[] array = new int[]{3, 1, 2, 4, 5, 6, 7, 8, 9, 0, 1, 1, 3, 4, 5, 6, 6, 2, 1, 3, 4, 5, 6, 7, 7, 8, 8, 10, 11, 23, 34, 56, 32, 21, 100, 233, 123, 43, 4324, 123, 43, 234, 4, 234, 234, 234, 234, 234, 123};
            long SELECTION_START_TIME = System.currentTimeMillis();
            selection(array);
            long SELECTION_DELTA_TIME = System.currentTimeMillis() - SELECTION_START_TIME;
            int[] array1 = new int[]{3, 1, 2, 4, 5, 6, 7, 8, 9, 0, 1, 1, 3, 4, 5, 6, 6, 2, 1, 3, 4, 5, 6, 7, 7, 8, 8, 10, 11, 23, 34, 56, 32, 21, 100, 233, 123, 43, 4324, 123, 43, 234, 4, 234, 234, 234, 234, 234, 123};

            long MERGE_START_TIME = System.currentTimeMillis();
            merge(array1);
            long MERGE_DELTA_TIME = System.currentTimeMillis() - MERGE_START_TIME;

            System.out.println("Selection finished in: " + SELECTION_DELTA_TIME);
            System.out.println("Merge finished in: " + MERGE_DELTA_TIME);

      }


      public static int[] selection(int[] arr)
      {

            for (int i = 0; i < arr.length - 1; i++)
            {
                  int min = i;
                  for (int j = i + 1; j < arr.length; j++)
                  {
                        if (arr[j] < arr[min])
                        {
                              min = j;
                        }
                  }
                  int temp = arr[i];
                  arr[i] = arr[min];
                  arr[min] = temp;
            }
            return arr;


      }

      public static int[] quick(int[] arr, int first, int last)
      {
            if (last <= first)
            {
                  return arr;
            }

            int pivot = arr[first];
            int pos = last;

            return new int[]{3};
      }

      public static int bs(int[] num, int num1)
      {

            int left = 0;
            int right = num.length - 1;


            while (left <= right)
            {
                  int midpoint = left + (right - left) / 2;
                  if (num[midpoint] == num1)
                  {
                        return midpoint;
                  }
                  else
                  {
                        if (num[midpoint] < num1)
                        {
                              left = midpoint + 1;
                        }
                        else
                        {
                              right = midpoint - 1;
                        }
                  }
            }

            return -1;
      }


      public static int bsR(int num[], int num1, int left, int right)
      {
            int mid = left + (right - left) / 2;

            if (num1 == num[mid]) return mid;
            if (num[mid] < num1)
            {
                  return bsR(num, num1, left + 1, right);
            }
            else return bsR(num, num1, left, right - 1);
      }

      public static int[] merge(int[] array)
      {
            if (array.length <= 1)
                  return array;

            int mindpoint = (array.length / 2);

            int[] left = new int[mindpoint];
            int[] right = new int[array.length % 2 == 0 ? mindpoint : mindpoint + 1];

            for (int i = 0; i < mindpoint; i++)
            {
                  left[i] = array[i];
            }

            for (int j = 0; j < right.length; j++)
            {
                  right[j] = array[mindpoint + j];
            }
            int[] result = new int[array.length];
            left = merge(left);
            right = merge(right);
            mergeS(left, right);

            return result;

      }

      private static int[] mergeS(int[] left, int[] right)
      {
            int[] result = new int[left.length + right.length];
            int leftPointer, rightPointer, resultPointer;
            leftPointer = rightPointer = resultPointer = 0;

            /**
             * Checking if the merge is possible
             * [4,5] left [6,7] right
             */
            int i = 0;
            while (leftPointer < left.length || rightPointer < right.length)
            {
                  /**
                   * Checking if both elements have elements
                   */
                  if (leftPointer < left.length && rightPointer < right.length)
                  {
                        if (left[leftPointer] < right[rightPointer])
                        {
                              result[rightPointer++] = left[leftPointer++];
                        }
                        else
                        {
                              result[leftPointer++] = right[rightPointer++];
                        }
                  }
                  /**
                   * Only elements in left
                   */
                  else if (leftPointer < left.length)
                  {
                        result[resultPointer++] = left[leftPointer++];
                  }
                  else
                  {
                        result[resultPointer++] = right[rightPointer++];
                  }
            }
            return result;
      }
}
