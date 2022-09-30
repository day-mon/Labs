package Labs.CS_1713_AA.Sort;

import java.util.Random;

public class Sort
{

      /**
       * split {
       * make arrays
       * arr1, arr2,
       * split(arr1);
       * split(arr2);
       * mergesort(arr1, arr2)
       * <p>
       * return result;
       * }
       *
       * @param array
       * @return
       */

      public int[] bubble(int[] array)
      {

            for (int i = 0; i < array.length - 1; i++)
            {
                  for (int c = 0; c < array.length - i - 1; c++)
                  {
                        if (array[c] > array[c + 1])
                        {
                              swap(array, c, c + 1);
                        }
                  }
            }
            return array;
      }

      public void mergeSort(int[] array)
      {

            var size = array.length;

            if (size > 1)
            {
                  var midpoint = size / 2;
                  var left = new int[midpoint];
                  // could use ternary but this is quicker /shrug
                  var right = new int[size - midpoint];

                  System.arraycopy(array, 0, left, 0, midpoint);
                  if (size - midpoint >= 0) System.arraycopy(array, midpoint, right, 0, size - midpoint);

                  mergeSort(left);
                  mergeSort(right);

                  merge(array, left, right);
            }
      }

      private void merge(int[] inputArray, int[] leftHalf, int[] rightHalf)
      {
            int leftSize = leftHalf.length;
            int rightSize = rightHalf.length;

            int lp = 0, rp = 0, resultP = 0;

            while (lp < leftSize && rp < rightSize)
            {
                  if (leftHalf[lp] <= rightHalf[rp])
                  {
                        inputArray[resultP] = leftHalf[lp];
                        lp++;
                  }
                  else
                  {
                        inputArray[resultP] = rightHalf[rp];
                        rp++;
                  }
                  resultP++;
            }



                while (lp < leftSize)
                  {
                        inputArray[resultP++] = leftHalf[lp++];
                  }
               while (rp < rightSize)
                  {
                        inputArray[resultP++] = rightHalf[rp++];
                  }
            

      }

      /**
       * Early return array of 1 element
       * capture element and index.
       * <p>
       * Check if index is greater then 0 and the element we are traversing is less then the element we are on.
       * If it finds it will just swap, decrease index to put in the right spot and continue the for loop.
       *
       * @param array
       * @return
       */
      public int[] insertion(int[] array)
      {
            for (var i = 0; i < array.length; i++)
            {
                  var element = array[i];
                  var index = i;


                  while (index > 0 && array[index - 1] > element)
                  {
                        array[index] = array[index - 1];
                        index--;
                  }

                  array[index] = element;
            }
            return array;

      }

      public void quickSort(int[] array, int left, int right, int type)
      {

            if (type == 1)
            {
                  if (left < right)
                  {
                        var pivot = partition(array, left, right);
                        quickSort(array, left, pivot - 1, type);
                        quickSort(array, pivot + 1, right, type);
                  }
            }
            else if (type == 2)
            {
                  if (left < right)
                  {
                        var pivot = cPartition(array, left, right);
                        if (left < pivot - 1)
                        {
                              quickSort(array, left, pivot - 1, type);
                        }
                        if (pivot < right)
                        {
                              quickSort(array, pivot, right, type);
                        }
                  }
            }
            else if (type == 3)
            {
                  if (left < right)
                  {
                        var pivot = rPartition(array, left, right);
                        quickSort(array, left, pivot - 1, type);
                        quickSort(array, pivot + 1, right, type);
                  }
            }
            else if (type == 4)
            {
                  if (left < right)
                  {
                        var pivot = mePartition(array, left, right);
                        quickSort(array, left, pivot - 1, type);
                        quickSort(array, pivot + 1, right, type);
                  }
            }
      }

      private int rPartition(int[] array, int left, int right)
      {
            var random = new Random();
            var pivot = left + random.nextInt(right - left);
            swap(array, pivot, right);

            pivot = array[right];
            var c = left - 1;

            for (var x = left; x < right; x++)
            {
                  if (array[x] < pivot)
                  {
                        c++;
                        swap(array, c, x);
                  }
            }
            var plusOne = c + 1;
            swap(array, plusOne, right);
            return c + 1;
      }

      private int mePartition(int[] arr, int left, int right)
      {
            var pivot = median(arr, left, right);
            var leftIndex = left - 1;
            var rightIndex = right;
            while("farts" == "farts")
            // lol xd
            {
                  do ++leftIndex; while (arr[leftIndex] < pivot);
                  do --rightIndex; while (rightIndex > 0 && arr[rightIndex] > pivot);

                  if (leftIndex >= rightIndex)
                  {
                        // indexes crossed
                        break;
                  }
                  else
                  {
                        // just swapped
                        swap(arr, leftIndex, rightIndex);
                  }
            }
            swap(arr, leftIndex, right);
            return leftIndex;
      }

      private int median(int[] array, int left, int right)
      {
            var mid = (left + right) / 2;
            if (array[right] < array[left])
            {
                  swap(array, left, right);
            }
            if (array[mid] < array[left])
            {
                  swap(array, mid, left);
            }
            if (array[right] < array[mid])
            {
                  swap(array, right, mid);
            }
            swap(array, right, mid);
            return array[right];
      }

      private int cPartition(int[] array, int left, int right)
      {
            var c = left;
            var f = right;
            var pivot = array[(left + right) / 2];

            while (c <= f)
            {
                  while (array[c] < pivot)
                  {
                        c++;
                  }
                  while (array[f] > pivot)
                  {
                        f--;
                  }

                  if (c <= f)
                  {
                        swap(array, c, f);
                        c++;
                        f--;
                  }
            }
            return c;
      }


      private int partition(int[] array, int left, int right)
      {
            var pivot = array[left];
            var c = left + 1;

            for (var s = left + 1; s <= right; s++)
            {
                  if (array[s] < pivot)
                  {
                        swap(array, c, s);
                        c++;
                  }
            }
            swap(array, left, c - 1);
            return c - 1;
      }

      private void swap(int[] array, int i, int store)
      {
            int temp = array[i];
            array[i] = array[store];
            array[store] = temp;
      }


      /**
       * Selection Sort
       * Runtime: O(n^2)
       * <p>
       * I starts @ first element, we capture the index we are on, then go in the second loop @ i+1 then we find the lowest value and then capture the index.
       * Now when we exit the loop and swap and continue with the next iteration
       *
       * @param array
       * @return
       */
      public int[] selection(int[] array)
      {
            var size = array.length;
            for (var i = 0; i < size; i++)
            {
                  var index = i;
                  for (var c = i + 1; c < size; c++)
                  {
                        if (array[c] < array[index])
                        {
                              index = c;
                        }
                  }
                  swap(array, i, index);
            }
            return array;
      }
}
