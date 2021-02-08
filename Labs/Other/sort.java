package Labs.Other;

public class sort {
    public static void main(String[] args) {

        long START_TIME = System.currentTimeMillis();
        selection(new int[] {3, 1, 2, 4, 5, 6, 7, 8, 9, 0, 1, 1, 3, 4, 5, 6, 6, 2, 1, 3, 4, 5, 6, 7, 7, 8, 8, 10, 11, 23, 34, 56 ,32, 21, 100, 233, 123, 43, 4324, 123, 43, 234, 4, 234, 234, 234, 234, 234,123});
        long DELTA_TIME = System.currentTimeMillis() - START_TIME;
        System.out.println("Finished in: " + DELTA_TIME);
    }



    public static int[] selection(int[]arr) {

        for (int i = 0; i < arr.length-1; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min=j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;

        
    }

    public static int[] quick(int[] arr, int first, int last) {
        if (last <= first) {
            return arr;
        }

        int pivot = arr[first];
        int pos = last;

        return new int [] {3};
    }
}
